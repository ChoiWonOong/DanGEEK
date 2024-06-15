package DanGEEK.app.repository;

import DanGEEK.app.domain.Member.MemberAnalyzeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAnalyzeRepository extends JpaRepository<MemberAnalyzeInfo, Long>{

}
