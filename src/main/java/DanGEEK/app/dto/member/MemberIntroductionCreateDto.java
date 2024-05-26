package DanGEEK.app.dto.member;

import DanGEEK.app.domain.Member;
import DanGEEK.app.domain.MemberIntroduction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberIntroductionCreateDto {
    private String name;
    private String major;
    private String grade;
    private String sex;
    private List<String> personality;
    private List<String> hobby;
    private String contents;
    public MemberIntroduction toMemberIntroduction(Member member){
        return MemberIntroduction.builder()
                .name(name)
                .major(major)
                .grade(grade)
                .sex(sex)
                .personality(personality)
                .hobby(hobby)
                .contents(contents)
                .member(member)
                .build();
    }
}
