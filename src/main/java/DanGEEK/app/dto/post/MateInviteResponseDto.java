package DanGEEK.app.dto.post;

import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import DanGEEK.app.util.SecurityUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MateInviteResponseDto extends PostResponseDto{
    private MemberIntroductionCreateDto memberIntroduction;
    private ChatRoomResponseDto chatRoomResponse;
    public MateInviteResponseDto(Long post_id, String title, String contents, String nickname, String post_type, MemberIntroductionCreateDto memberIntroductionCreateDto, ChatRoomResponseDto chatRoomResponseDto) {
        super(post_id, SecurityUtil.getCurrentMemberId(),title, contents, nickname, post_type, chatRoomResponseDto);
        this.memberIntroduction = memberIntroductionCreateDto;
    }
}
