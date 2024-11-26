package DanGEEK.app.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatRoomMemberCreateResponseDto {
    private String status;
    private Long roomId;
    private Long userId;

}
