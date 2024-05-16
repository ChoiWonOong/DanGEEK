package DanGEEK.app.domain;

import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ChatRoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long roomId;

    public ChatRoomMember(Long userId, Long roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }
    public ChatRoomMemberCreateResponseDto toResponseDto(){
        return new ChatRoomMemberCreateResponseDto("success", roomId, userId);
    }
}
