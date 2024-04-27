package DanGEEK.app.service;

import DanGEEK.app.domain.Member;
import DanGEEK.app.domain.MemberIntroduction;
import DanGEEK.app.dto.MemberIntroductionCreateDto;
import DanGEEK.app.dto.MyPageDto;
import DanGEEK.app.repository.MemberIntroductionRepository;
import DanGEEK.app.repository.MemberRepository;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberIntroductionRepository memberIntroductionRepository;

    @Transactional
    public MemberIntroductionCreateDto writeIntroduction(MemberIntroductionCreateDto memberIntroductionCreateDto){
        MemberIntroduction memberIntroduction = memberIntroductionCreateDto.toMemberIntroduction();
        Member member = getMe();
        member.writeIntroduction(memberIntroduction);
        memberIntroductionRepository.save(memberIntroduction);
        return memberIntroductionCreateDto;
    }
    public MyPageDto getMyPage(){
        Member member = getMe();
        return member.MemberToMyPageDto();
    }
    public MemberIntroductionCreateDto getMyIntroduction(){
        Member member = getMe();
        MemberIntroduction memberIntroduction = member.getIntroduction();
        return memberIntroduction.introductionToDto();
    }
    public MemberIntroductionCreateDto getMemberIntroduction(Long id){
        Member member = memberRepository.findById(id).get();
        MemberIntroduction memberIntroduction = member.getIntroduction();
        return memberIntroduction.introductionToDto();
    }
    private Member getMe(){
        return memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
    }
}
