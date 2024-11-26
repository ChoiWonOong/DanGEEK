package DanGEEK.app.dto.post;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Post;
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
    public MateInviteResponseDto(Post post, Member member, ChatRoomResponseDto chatRoomResponseDto) {
        super(post.getId(), member.getId(),post.getTitle(), post.getContents(), member.getNickname(), post.getType().getString(), chatRoomResponseDto);
        this.memberIntroduction = member.getIntroduction().toIntroductionDto();
    }
}
