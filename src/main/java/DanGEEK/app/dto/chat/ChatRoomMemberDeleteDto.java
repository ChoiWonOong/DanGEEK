package DanGEEK.app.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomMemberDeleteDto {
    String status;
    Long roomId;
    Long userId;

    public ChatRoomMemberDeleteDto(Long roomId , Long userId) {
        this.roomId = roomId;
        this.userId = userId;
        this.status = "success";
    }
}
