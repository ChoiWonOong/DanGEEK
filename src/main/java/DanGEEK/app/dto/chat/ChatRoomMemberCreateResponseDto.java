package DanGEEK.app.dto.chat;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChatRoomMemberCreateResponseDto {
    private String status;
    private Long roomId;
    private Long userId;

}
