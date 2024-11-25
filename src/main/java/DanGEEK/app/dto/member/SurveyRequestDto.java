package DanGEEK.app.dto.member;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberAnalyzeInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SurveyRequestDto {
    private Cleanliness cleanliness;
    @Getter
    private int wakeTime;
    @Getter
    private int sleepTime;
    @Getter
    private List<String> hobbies;
    @Getter
    private static class Cleanliness {
        private int answer1;
        private int answer2;
        private int answer3;
        private int answer4;
        private int answer5;
        private int answer6;
        private int answer7;
        private int answer8;
        private int answer9;
        private int answer10;
    }

    public List<Integer> getCleanliness() {
        return List.of(
            cleanliness.getAnswer1(),
            cleanliness.getAnswer2(),
            cleanliness.getAnswer3(),
            cleanliness.getAnswer4(),
            cleanliness.getAnswer5(),
            cleanliness.getAnswer6(),
            cleanliness.getAnswer7(),
            cleanliness.getAnswer8(),
            cleanliness.getAnswer9(),
            cleanliness.getAnswer10()
        );
    }
    @Builder
    public MemberAnalyzeInfo toEntity(Member member, SurveyRequestDto dto) {
        return new MemberAnalyzeInfo(member, dto);
    }
}
