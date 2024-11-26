package DanGEEK.app.domain.Member;

import DanGEEK.app.dto.member.MemberAnalyzeInfoDto;
import DanGEEK.app.dto.member.SurveyRequestDto;
import DanGEEK.app.util.StringListConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
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

/*
    @Convert(converter = StringListConverter.class)
    @Column(name = "hobbies")
    List<String> hobbies = new ArrayList<>();
*/

    public MemberAnalyzeInfo(Member member, SurveyRequestDto dto){
        this.cleanliness = dto.getCleanliness();
        this.member = member;
        this.wakeTime = dto.getWakeTime();
        if(dto.getSleepTime() < 12){
            log.info("sleepTime : {}", dto.getSleepTime());
            this.sleepTime = dto.getSleepTime() + 24;
        }else{
            log.info("sleepTime : {}", dto.getSleepTime());
            this.sleepTime = dto.getSleepTime();
        }
        //this.hobbies = dto.getHobbies();
    }
    public MemberAnalyzeInfo update(SurveyRequestDto dto){
        this.cleanliness = dto.getCleanliness();
        this.wakeTime = dto.getWakeTime();
        if(dto.getSleepTime() < 12){
            log.info("sleepTime : {}", dto.getSleepTime());
            this.sleepTime = dto.getSleepTime() + 24;
        }else{
            log.info("sleepTime : {}", dto.getSleepTime());
            this.sleepTime = dto.getSleepTime();
        }
        return this;
    }
    public MemberAnalyzeInfoDto toDto(){
        return new MemberAnalyzeInfoDto(this);
    }
    @Override
    public String toString(){
        return "MemberAnalyzeInfo{" +
                "id=" + id +
                ", member=" + member +
                ", cleanliness=" + cleanliness +
                ", wakeTime=" + wakeTime +
                ", sleepTime=" + sleepTime +
                //", hobbies=" + hobbies +
                '}';
    }

    public double getCosineSimilarity(MemberAnalyzeInfo other){
        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;
        List<Integer> myVector = new ArrayList<>();
        myVector.addAll(cleanliness);
        myVector.add(getWakeTime()/2);
        myVector.add(getSleepTime()/2);
        log.info("myVector : {}", myVector);
        List<Integer> otherVector = new ArrayList<>();
        otherVector.addAll(other.cleanliness);
        otherVector.add(other.getWakeTime()/2);
        otherVector.add(other.getSleepTime()/2);
        log.info("otherVector : {}", otherVector);
        for (int i = 0; i < otherVector.size(); i++) {
            dotProduct += otherVector.get(i) * myVector.get(i);
            norm1 += Math.pow(otherVector.get(i), 2);
            norm2 += Math.pow(myVector.get(i), 2);
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
