package DanGEEK.app.domain.Member;

import DanGEEK.app.dto.member.MemberAnalyzeInfoDto;
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
    private List<Integer> cleanliness = new ArrayList<>();

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
    public MemberAnalyzeInfoDto toDto(){
        return new MemberAnalyzeInfoDto(this);
    }

    public double getCosineSimilarity(MemberAnalyzeInfo other){
        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;
        List<Integer> myVector = this.cleanliness;
        List<Integer> otherVector = other.cleanliness;
        for (int i = 0; i < otherVector.size(); i++) {
            dotProduct += otherVector.get(i) * myVector.get(i);
            norm1 += Math.pow(otherVector.get(i), 2);
            norm2 += Math.pow(myVector.get(i), 2);
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
