package DanGEEK.app.repository;

import DanGEEK.app.domain.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);
    Optional<Member> findByUsername(String username);
    boolean existsByNickname(String nickname);
    Optional<Member> findByNickname(String nickname);

    List<Member> findAllByPutOnRecommend(boolean putOutRecommend);
}
