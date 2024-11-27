package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Hobby;
import DanGEEK.app.domain.Member.*;
import DanGEEK.app.dto.MyPageDto;
import DanGEEK.app.dto.member.MemberAnalyzeInfoDto;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import DanGEEK.app.dto.member.SurveyRequestDto;
import DanGEEK.app.repository.*;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberIntroductionRepository memberIntroductionRepository;
    private final MemberAnalyzeInfoRepository memberAnalyzeInfoRepository;
    private final MemberHobbyRepository memberHobbyRepository;
    private final S3Service s3Service;
    private final MemberAnalyzeRepository memberAnalyzeRepository;
    private final AnalysisSimilarityRepository analysisSimilarityRepository;

    public MemberIntroduction introductionDtoToEntity(MemberIntroductionCreateDto memberIntroductionCreateDto) {
        Member member = getMe();
        List<Hobby> hobbies = memberIntroductionCreateDto.getHobbies().stream().map(Hobby::findHobby).toList();
        hobbies.forEach(hobby -> {
            MemberHobby memberHobby = new MemberHobby(member, hobby);
            memberHobbyRepository.save(memberHobby);
        });
        List<MemberHobby> memberHobbies = new ArrayList<>(memberHobbyRepository.findByMember(member));
        return memberIntroductionCreateDto.toMemberIntroduction(member, memberHobbies);
    }

    public MyPageDto updateImage(MultipartFile image){
        String imageUrl = s3Service.upload(image);
        Member member = getMe();
        member.setImageUrl(imageUrl);
        memberRepository.save(member);
        return member.MemberToMyPageDto();
    }
    @Transactional
    public MemberIntroductionCreateDto writeIntroduction(MemberIntroductionCreateDto memberIntroductionCreateDto){
        try{
            Member member = getMe();
            MemberIntroduction memberIntroduction = introductionDtoToEntity(memberIntroductionCreateDto);
            log.info("memberIntroduction contents : {}",memberIntroduction.getContents());
            member.writeIntroduction(memberIntroduction);
            memberIntroductionRepository.save(memberIntroduction);
            return member.getIntroduction().toIntroductionDto();
        }catch (RestApiException e){
            StackTraceElement stackTrace = e.getStackTrace()[0];
            log.error(e.getMessage(), stackTrace);
            throw new RestApiException(ErrorCode.NOT_EXIST_ERROR, stackTrace);
        }
    }
    public MyPageDto getMyPage(){
        Member member = getMe();
        return member.MemberToMyPageDto();
    }
    public MemberIntroductionCreateDto getMyIntroduction(){
        Member member = getMe();
        MemberIntroduction memberIntroduction = member.getIntroduction();
        return memberIntroduction.toIntroductionDto();
    }
    public MemberIntroductionCreateDto getMemberIntroduction(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        MemberIntroduction memberIntroduction = member.getIntroduction();
        return memberIntroduction.toIntroductionDto();
    }
    public MyPageDto releaseMember(){
        Member member = getMe();
        member.changePutOutToRecommend(true);
        memberRepository.save(member);
        return member.MemberToMyPageDto();
    }
    public MyPageDto holdMember(){
        Member member = getMe();
        member.changePutOutToRecommend(false);
        memberRepository.save(member);
        return member.MemberToMyPageDto();
    }
    public Member findMemberById(Long id){
        return memberRepository.findById(id).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public Member getMe(){
        return memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }

    public List<MemberIntroductionCreateDto> getRecommendedInstruction() {
        Member me = getMe();
        log.info("me : {}", me.getNickname());
        MemberAnalyzeInfo myInfo = memberAnalyzeInfoRepository.findByMember(me).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        log.info("myInfo : {}", myInfo.getId());
        List<AnalysisSimilarity> mySimilarities = analysisSimilarityRepository.findByAnalysisInfoAndSimilarityGreaterThanEqual(myInfo, 0.9);
        if(mySimilarities.isEmpty()) throw new RestApiException(ErrorCode.NOT_EXIST_ERROR);
        List<MemberIntroduction> introductions = new ArrayList<>();
        for(AnalysisSimilarity similarity : mySimilarities){
            Member other = similarity.getOtherAnalysis(myInfo).getMember();
            log.info("other : {}", other.getNickname());
            if(other.getIntroductionWritten() && other.getPutOnRecommend()){
                introductions.add(other.getIntroduction());
            }
        }
        return introductions.stream().map(MemberIntroduction::toIntroductionDto).toList();
    }

    public SurveyRequestDto writeSurvey(SurveyRequestDto surveyDto) {
        Member member = getMe();
        Optional<MemberAnalyzeInfo> optionalMemberAnalyzeInfo = memberAnalyzeInfoRepository.findByMember(member);
        MemberAnalyzeInfo memberAnalyzeInfo;
        if(optionalMemberAnalyzeInfo.isPresent()){
            memberAnalyzeInfo = optionalMemberAnalyzeInfo.get();
            memberAnalyzeInfo = memberAnalyzeInfo.update(surveyDto);
            memberAnalyzeInfo = memberAnalyzeInfoRepository.save(memberAnalyzeInfo);
        }else{
            member.surveyDone();
            memberAnalyzeInfo = new MemberAnalyzeInfo(member, surveyDto);
            memberAnalyzeInfo = memberAnalyzeInfoRepository.save(memberAnalyzeInfo);
        }
        calculateSimilarity(memberAnalyzeInfo);
        return surveyDto;
    }
    @Transactional
    void calculateSimilarity(MemberAnalyzeInfo myInfo){
        log.info("myInfo : {}", myInfo.toString());
        List<MemberAnalyzeInfo> analyzeInfos = memberAnalyzeInfoRepository.findAll();
        if(analyzeInfos.isEmpty()) throw new RestApiException(ErrorCode.NOT_EXIST_ERROR);
        if(analysisSimilarityRepository.findByAnalyzeInfo(myInfo).isEmpty()){
            log.info("similarity is empty");
            for (MemberAnalyzeInfo othersAnalyzeInfo : analyzeInfos) {
                if(othersAnalyzeInfo.getMember().equals(myInfo.getMember())) continue;
                double similarity = othersAnalyzeInfo.getCosineSimilarity(myInfo);
                analysisSimilarityRepository.save(new AnalysisSimilarity(myInfo, othersAnalyzeInfo, similarity));
                //similarityRepository.save(new Similarity(me, other, similarity));
            }
        }else{
            log.info("similarity is not empty");
            List<AnalysisSimilarity> mymySimilarities = analysisSimilarityRepository.findByAnalyzeInfo(myInfo);
            for (AnalysisSimilarity mySimilarity : mymySimilarities) {
                mySimilarity.update();
                analysisSimilarityRepository.save(mySimilarity);
            }
        }
    }
    public MemberAnalyzeInfoDto getAnalyzeInfo(Long id) {
        return memberAnalyzeRepository.findById(id).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR)).toDto();
    }
    public Member findMemberByNickname(String nickname){
        return memberRepository.findByNickname(nickname).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
}
