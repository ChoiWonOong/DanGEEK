package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Hobby;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberAnalyzeInfo;
import DanGEEK.app.domain.Member.MemberHobby;
import DanGEEK.app.domain.Member.MemberIntroduction;
import DanGEEK.app.dto.member.MemberCreateResponseDto;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import DanGEEK.app.dto.MyPageDto;
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

    public MemberIntroduction introductionDtoToEntity(MemberIntroductionCreateDto memberIntroductionCreateDto) {
        Member member = getMe();
        List<Hobby> hobbies = memberIntroductionCreateDto.getHobbies().stream().map(Hobby::findHobby).toList();
        log.info("hobbies : {}", hobbies.stream().map(Hobby::toString).toList());
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
        Member member = getMe();
        MemberIntroduction memberIntroduction = introductionDtoToEntity(memberIntroductionCreateDto);
        log.info("memberIntroduction contents : {}",memberIntroduction.getContents());
        member.writeIntroduction(memberIntroduction);
        memberIntroductionRepository.save(memberIntroduction);
        return member.getIntroduction().toIntroductionDto();
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
        List<Member> recommendMembers = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR)).getRecommendMembers();
        //List<Member> members = memberRepository.findAllByPutOnRecommend(true);
        List<MemberIntroduction> introductions = recommendMembers.stream().map(Member::getIntroduction).toList();
        return introductions.stream().map(MemberIntroduction::toIntroductionDto).toList();
    }

    public SurveyRequestDto writeSurvey(SurveyRequestDto surveyDto) {
        Member member = getMe();
        MemberAnalyzeInfo memberAnalyzeInfo = new MemberAnalyzeInfo(member, surveyDto);
        memberAnalyzeInfoRepository.save(memberAnalyzeInfo);
        return surveyDto;
    }

    public MemberAnalyzeInfo writeAnalyzeInfo(SurveyRequestDto surveyRequestDto) {
        Member member = getMe();
        MemberAnalyzeInfo memberAnalyzeInfo = surveyRequestDto.toEntity(member, surveyRequestDto);
        return memberAnalyzeRepository.save(memberAnalyzeInfo);
    }
    public MemberAnalyzeInfo getAnalyzeInfo(Long id) {
        return memberAnalyzeRepository.findById(id).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public Member findMemberByNickname(String nickname){
        return memberRepository.findByNickname(nickname).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
}
