package DanGEEK.app.dto.member;

import DanGEEK.app.domain.Member.MemberAnalyzeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberAnalyzeInfoDto {
    private List<Integer> cleanliness;
    @Getter
    private int wakeTime;
    @Getter
    private int sleepTime;
    @Getter
    private List<String> hobbies;

    public MemberAnalyzeInfoDto(MemberAnalyzeInfo memberAnalyzeInfo) {
        this.cleanliness = memberAnalyzeInfo.getCleanliness();
        this.wakeTime = memberAnalyzeInfo.getWakeTime();
        this.sleepTime = memberAnalyzeInfo.getSleepTime();
        //this.hobbies = memberAnalyzeInfo.getHobbies();
    }
}
