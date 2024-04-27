package DanGEEK.app.dto;

import DanGEEK.app.domain.MemberIntroduction;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberIntroductionCreateDto {
    private String name;
    private String major;
    private String grade;
    private String sex;
    private List<String> personalty;
    private List<String> hobby;
    public MemberIntroduction toMemberIntroduction(){
        return MemberIntroduction.builder()
                .name(name)
                .major(major)
                .grade(grade)
                .sex(sex)
                .personality(personalty)
                .hobby(hobby)
                .build();
    }
}
