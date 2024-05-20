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
    private String sender;
    public Chat toEntity(MessageType type) {
        return new Chat(roomId, sender, message, type);
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
