package DanGEEK.app.domain.Chat;

import DanGEEK.app.domain.MessageType;
import DanGEEK.app.dto.chat.ChatResponseDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Long senderId;
    @Setter
    private String senderNickname;
    private String message;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @Column(name = "is_bad_words")
    private boolean isBadWords = false;

    public Chat(Long roomId, Long senderId, String message, MessageType type) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.message = message;
        this.created_at = LocalDateTime.now();
        this.type = type;
    }
    public ChatResponseDto toResponseDto(){
        return new ChatResponseDto(type, roomId, message, senderId, senderNickname, created_at);
    }
    public static List<ChatResponseDto> toDtoList(List<Chat> chatList){
        List<ChatResponseDto> chatDtos = new ArrayList<>();
        for(Chat c : chatList){
            ChatResponseDto dto = c.toResponseDto();
            chatDtos.add(dto);
        }
        return chatDtos;
    }
    public void setBadWords(){
        isBadWords = true;
    }
}
