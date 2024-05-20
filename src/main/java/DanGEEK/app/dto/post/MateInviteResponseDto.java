package DanGEEK.app.dto.post;

import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import lombok.Getter;

@Getter
public class MateInviteResponseDto extends PostResponseDto{
    private MemberIntroductionCreateDto memberIntroductionCreateDto;
    public MateInviteResponseDto(Long post_id, String title, String contents, String nickname, PostType post_type) {
        super(post_id, title, contents, nickname, post_type);
    }
    public void setMemberIntroductionCreateDto(MemberIntroductionCreateDto memberIntroductionCreateDto) {
        this.memberIntroductionCreateDto = memberIntroductionCreateDto;
    }
}
