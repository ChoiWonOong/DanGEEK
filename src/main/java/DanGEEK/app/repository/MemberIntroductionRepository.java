package DanGEEK.app.repository;

import DanGEEK.app.domain.Member;
import DanGEEK.app.domain.MemberIntroduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberIntroductionRepository extends JpaRepository<MemberIntroduction, Long> {
    Optional<MemberIntroduction> findByMember(Member member);
}
