package DanGEEK.app.domain;

import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ChatRoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member userId;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom roomId;

    private int unreadCount = 0;
    public ChatRoomMember(Member member, ChatRoom chatRoom) {
        this.userId = member;
        this.roomId = chatRoom;
    }
    public ChatRoomMemberCreateResponseDto toResponseDto(){
        return new ChatRoomMemberCreateResponseDto("success", getRoomId(), getMemberId());
    }

    public Long getMemberId() {
        return userId.getId();
    }
    public Long getRoomId(){
        return roomId.getRoomId();
    }
    public void increaseUnreadCount(){
        unreadCount++;
    }
    public void resetUnreadCount(){
        unreadCount = 0;
    }
}
