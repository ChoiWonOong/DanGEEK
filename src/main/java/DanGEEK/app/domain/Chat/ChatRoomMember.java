package DanGEEK.app.domain.Chat;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ChatMemberId.class)
public class ChatRoomMember {
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Id
    private Member userId;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @Id
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
