package DanGEEK.app.dto.chat;

import DanGEEK.app.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatResponseDto {
    private Chat.MessageType type;
    private Long roomId;
    private String message;
    private String sender;
    private LocalDateTime created_at;
}
