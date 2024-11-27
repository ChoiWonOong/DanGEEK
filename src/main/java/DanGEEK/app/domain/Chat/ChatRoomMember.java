package DanGEEK.app.domain.Chat;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ChatMemberId.class)
public class ChatRoomMember {
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Id
    @Getter
    private Member userId;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @Id
    @Getter
    private ChatRoom roomId;

    private int unreadCount = 0;
    public ChatRoomMember(Member member, ChatRoom chatRoom) {
        this.userId = member;
        this.roomId = chatRoom;
    }
    public ChatRoomMemberCreateResponseDto toResponseDto(){
        return new ChatRoomMemberCreateResponseDto("success", getRoomId().getRoomId(), getMemberId());
    }

    public Long getMemberId() {
        return userId.getId();
    }
    public void increaseUnreadCount(){
        unreadCount++;
    }
    public void resetUnreadCount(){
        unreadCount = 0;
    }
}
