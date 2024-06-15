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
        private String answer1;
        private String answer2;
        private String answer3;
        private String answer4;
        private String answer5;
        private String answer6;
        private String answer7;
        private String answer8;
        private String answer9;
        private String answer10;
    }

    public List<String> getCleanliness() {
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
