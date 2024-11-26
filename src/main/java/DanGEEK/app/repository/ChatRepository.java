package DanGEEK.app.repository;

import DanGEEK.app.domain.Chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long>{
    List<Chat> findAllByRoomId(Long roomId);
    void deleteAllByRoomId(Long roomId);
}
