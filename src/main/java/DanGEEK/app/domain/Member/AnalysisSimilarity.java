package DanGEEK.app.domain.Member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AnalysisSimilarityId.class)
@Getter
public class AnalysisSimilarity {
    @ManyToOne
    @JoinColumn(name = "analyze_info1_id")
    @Id
    private MemberAnalyzeInfo analysis1Id;
    @ManyToOne
    @JoinColumn(name = "analyze_info2_id")
    @Id
    private MemberAnalyzeInfo analysis2Id;
    private double similarity;

    public AnalysisSimilarity(MemberAnalyzeInfo analyzeInfo1, MemberAnalyzeInfo analyzeInfo2) {
        this.analysis1Id = analyzeInfo1;
        this.analysis2Id = analyzeInfo2;
    }

    public boolean contains(MemberAnalyzeInfo analyzeInfo){
        return analysis1Id.equals(analyzeInfo) || analysis2Id.equals(analyzeInfo);
    }
    public void update(){
        double similarity = this.analysis1Id.getCosineSimilarity(analysis2Id);
        this.similarity = similarity;
    }
    public MemberAnalyzeInfo getOtherAnalysis(MemberAnalyzeInfo analyzeInfo){
        return analysis1Id.equals(analyzeInfo) ? analysis2Id : analysis1Id;
    }
}
