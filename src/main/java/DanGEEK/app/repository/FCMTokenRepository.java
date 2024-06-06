package DanGEEK.app.repository;

import DanGEEK.app.domain.FCMToken;
import DanGEEK.app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FCMTokenRepository extends JpaRepository<FCMToken, Long> {
    boolean existsByMember(Member member);

    FCMToken findByMember(Member member);
}
