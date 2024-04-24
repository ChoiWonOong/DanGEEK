package DanGEEK.app.repository;

import DanGEEK.app.domain.MemberIntroduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberIntroductionRepository extends JpaRepository<MemberIntroduction, Long> {
}
