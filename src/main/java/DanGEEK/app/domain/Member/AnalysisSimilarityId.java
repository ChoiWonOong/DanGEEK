package DanGEEK.app.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisSimilarityId implements Serializable {
    private Long analysis1Id;
    private Long analysis2Id;
}
