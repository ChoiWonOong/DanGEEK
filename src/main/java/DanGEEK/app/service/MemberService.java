package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Member;
import DanGEEK.app.domain.MemberIntroduction;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import DanGEEK.app.dto.MyPageDto;
import DanGEEK.app.repository.MemberIntroductionRepository;
import DanGEEK.app.repository.MemberRepository;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberIntroductionRepository memberIntroductionRepository;

    @Transactional
    public MemberIntroductionCreateDto writeIntroduction(MemberIntroductionCreateDto memberIntroductionCreateDto){
        Member member = getMe();
        MemberIntroduction memberIntroduction = memberIntroductionCreateDto.toMemberIntroduction(member);
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
    private Member getMe(){
        return memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }

    public List<MemberIntroductionCreateDto> getRecommendedInstruction() {
        List<Member> recommendMembers = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR)).getRecommendMembers();
        //List<Member> members = memberRepository.findAllByPutOnRecommend(true);
        List<MemberIntroduction> introductions = recommendMembers.stream().map(Member::getIntroduction).toList();
        return introductions.stream().map(MemberIntroduction::toIntroductionDto).toList();
    }
}
