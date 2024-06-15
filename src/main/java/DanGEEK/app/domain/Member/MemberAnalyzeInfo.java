package DanGEEK.app.domain.Member;

import DanGEEK.app.dto.member.SurveyRequestDto;
import DanGEEK.app.util.StringListConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "cleanliness")
    @Convert(converter = StringListConverter.class)
    private List<String> cleanliness = new ArrayList<>();

    private int wakeTime;
    private int sleepTime;

    @Convert(converter = StringListConverter.class)
    @Column(name = "hobbies")
    List<String> hobbies = new ArrayList<>();

    public MemberAnalyzeInfo(Member member, SurveyRequestDto dto){
        this.cleanliness = dto.getCleanliness();
        this.member = member;
        this.wakeTime = dto.getWakeTime();
        this.sleepTime = dto.getSleepTime();
        this.hobbies = dto.getHobbies();
    }
}
