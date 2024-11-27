package DanGEEK.app.dto.chat;

import DanGEEK.app.domain.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatResponseDto {
    private MessageType type;
    private Long roomId;
    private String message;
    private Long senderId;
    private String senderNickname;
    @Setter
    private boolean isBadWords = false;
    private LocalDateTime created_at;
}
