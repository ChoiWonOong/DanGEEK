package DanGEEK.app.dto.chat;

import DanGEEK.app.domain.Chat;
import DanGEEK.app.domain.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestDto {
    private Long roomId;
    private String message;
    private Long senderId;
    public Chat toEntity(MessageType type) {
        return new Chat(roomId, senderId, message, type);
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
