package DanGEEK.app.repository;

import DanGEEK.app.domain.Member.AnalysisSimilarity;
import DanGEEK.app.domain.Member.MemberAnalyzeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnalysisSimilarityRepository extends JpaRepository<AnalysisSimilarity, Long> {
    @Query("select ms from AnalysisSimilarity ms where ms.analysis1Id = :analysis or ms.analysis2Id = :analysis")
    List<AnalysisSimilarity> findByAnalyzeInfo(@Param("analysis")MemberAnalyzeInfo analysis);
    @Query("select ms from AnalysisSimilarity ms where ms.analysis1Id = :analysis or ms.analysis2Id = :analysis and ms.similarity >= :similarity order by ms.similarity desc")
    List<AnalysisSimilarity> findByAnalysisInfoAndSimilarityGreaterThanEqual(@Param("analysis")MemberAnalyzeInfo analysis, @Param("similarity") double similarity);
}
