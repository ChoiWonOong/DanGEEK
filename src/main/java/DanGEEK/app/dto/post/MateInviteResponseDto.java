package DanGEEK.app.dto.post;

import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import lombok.Getter;

@Getter
public class MateInviteResponseDto extends PostResponseDto{
    private MemberIntroductionCreateDto memberIntroduction;
    private ChatRoomResponseDto chatRoomResponseDto;
    public MateInviteResponseDto(Long post_id, String title, String contents, String nickname, String post_type, MemberIntroductionCreateDto memberIntroductionCreateDto, ChatRoomResponseDto chatRoomResponseDto) {
        super(post_id, title, contents, nickname, post_type);
    }
    public void setMemberIntroductionCreateDto(MemberIntroductionCreateDto memberIntroductionCreateDto) {
        this.memberIntroduction = memberIntroductionCreateDto;
    }

}
