package DanGEEK.app.dto.chat;

import DanGEEK.app.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestDto {
    private Long roomId;
    private String sender;
    private String message;

    public Chat toEntity() {
        return new Chat(roomId, sender, message);
    }
}
