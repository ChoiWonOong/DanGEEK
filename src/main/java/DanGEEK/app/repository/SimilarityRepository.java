package DanGEEK.app.repository;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.Similarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimilarityRepository extends JpaRepository<Similarity, Long> {
    List<Similarity> findByMembersOrderBySimilarity(Member member);
}
