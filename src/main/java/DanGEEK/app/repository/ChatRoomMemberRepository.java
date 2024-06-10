package DanGEEK.app.repository;

import DanGEEK.app.domain.ChatRoom;
import DanGEEK.app.domain.ChatRoomMember;
import DanGEEK.app.domain.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    void deleteAllByRoomIdAndUserId(ChatRoom chatroom, Member member);
    List<ChatRoomMember> findAllByRoomId(ChatRoom chatroom);
    @Query("select m from ChatRoomMember m where m.roomId = :chatRoom")
    List<Member> findAllMemberByRoomId(@Param("chatRoom") ChatRoom chatRoom);
    List<ChatRoomMember> findAllByUserId(Member member);
}
