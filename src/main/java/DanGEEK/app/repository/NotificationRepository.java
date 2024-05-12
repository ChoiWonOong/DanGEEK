package DanGEEK.app.repository;

import DanGEEK.app.domain.Member;
import DanGEEK.app.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByReceiver(Member receiver);
}
