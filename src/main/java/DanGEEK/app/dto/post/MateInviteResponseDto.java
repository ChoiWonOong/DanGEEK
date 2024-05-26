package DanGEEK.app.dto.post;

import DanGEEK.app.domain.MemberIntroduction;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MateInviteResponseDto extends PostResponseDto{
    private MemberIntroductionCreateDto memberIntroductionCreateDto;
    public MateInviteResponseDto(Long post_id, String title, String contents, String nickname, String post_type) {
        super(post_id, title, contents, nickname, post_type);
    }
    public void setMemberIntroductionCreateDto(MemberIntroductionCreateDto memberIntroductionCreateDto) {
        this.memberIntroductionCreateDto = memberIntroductionCreateDto;
    }
}
