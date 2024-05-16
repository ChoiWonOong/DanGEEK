package DanGEEK.app.repository;

import DanGEEK.app.domain.ChatRoom;
import DanGEEK.app.domain.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    void deleteByRoomIdAndUserId(Long roomId, Long userId);
    List<ChatRoom> findAllByRoomId(Long roomId);
    @Query("select m.nickname from ChatRoomMember c join Member m on c.userId = m.id where c.roomId = :roomId")
    List<String> findAllMemberNameByRoomId(Long roomId);
}
