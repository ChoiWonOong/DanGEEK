package DanGEEK.app.dto.chat;

import DanGEEK.app.domain.Chat;
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
    private Long senderId;
    public Chat toEntity(MessageType type) {
        return new Chat(roomId, senderId, message, type);
    }

}
