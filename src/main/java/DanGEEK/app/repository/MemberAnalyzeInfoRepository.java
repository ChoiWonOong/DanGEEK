package DanGEEK.app.repository;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberAnalyzeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAnalyzeInfoRepository extends JpaRepository<MemberAnalyzeInfo, Long>{
    Optional<MemberAnalyzeInfo> findByMember(Member member);
}
