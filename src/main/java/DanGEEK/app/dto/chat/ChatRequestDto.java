package DanGEEK.app.dto.chat;

import DanGEEK.app.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRequestDto {
    private Long roomId;
    private String message;
    private String sender;
    public Chat toEntity() {
        return new Chat(roomId, sender, message);
    }
}
