package DanGEEK.app.dto.post;

import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PostResponseDto {
    private Long post_id;
    private Long memberId;
    private String title;
    private String contents;
    private String nickname;
    private String post_type;
    private ChatRoomResponseDto chatRoomResponseDto;
}
