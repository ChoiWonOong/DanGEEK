package DanGEEK.app.dto;

import DanGEEK.app.domain.MemberIntroduction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberIntroductionCreateDto {
    private String name;
    private String major;
    private String grade;
    private String sex;
    private List<String> personality;
    private List<String> hobby;
    public MemberIntroduction toMemberIntroduction(){
        return MemberIntroduction.builder()
                .name(name)
                .major(major)
                .grade(grade)
                .sex(sex)
                .personality(personality)
                .hobby(hobby)
                .build();
    }
}
