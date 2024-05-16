package DanGEEK.app.domain;

import DanGEEK.app.dto.chat.ChatResponseDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private MessageType type;
    private Long roomId;
    private String sender;
    private String message;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;

    public Chat(Long roomId, String sender, String message, MessageType type) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.created_at = LocalDateTime.now();
        this.type = type;
    }
    public static List<ChatResponseDto> toDtoList(List<Chat> chatList){
        List<ChatResponseDto> chatDtos = new ArrayList<>();
        for(Chat c : chatList){
            ChatResponseDto dto = new ChatResponseDto(c.type, c.roomId, c.sender, c.message, c.created_at);
            chatDtos.add(dto);
        }
        return chatDtos;
    }
}
