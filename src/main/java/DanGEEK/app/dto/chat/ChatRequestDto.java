package DanGEEK.app.dto.chat;

import DanGEEK.app.domain.Chat.Chat;
import DanGEEK.app.domain.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestDto {
    @Setter
    private Long roomId;
    @Setter
    private String message;

    public ChatRequestDto(Long id) {
        this.roomId = id;
    }

    public Chat toEntity(MessageType type) {
        return new Chat(roomId, message, type);
    }
    @Setter
    private Long senderId;
    @Override
    public String toString() {
        return "ChatRequestDto{" +
                "roomId=" + roomId +
                ", message='" + message + '\'' +
                ", senderId=" + senderId +
                '}';
    }
}
