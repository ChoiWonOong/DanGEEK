package DanGEEK.app.repository;

import DanGEEK.app.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("select r from ChatRoom r where r.roomId in :roomIds")
    List<ChatRoom> findAllById(List<Long> roomIds);
}
