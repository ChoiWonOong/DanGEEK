package DanGEEK.app.domain.Member;

import DanGEEK.app.dto.member.SurveyRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MemberAnalyzeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private int cleanliness;
    private int wakeTime;
    private int sleepTime;
    public MemberAnalyzeInfo(Member member, SurveyRequestDto dto){
        this.member = member;
        this.cleanliness = dto.getCleanliness();
        this.wakeTime = dto.getWakeTime();
        this.sleepTime = dto.getSleepTime();
    }
}
