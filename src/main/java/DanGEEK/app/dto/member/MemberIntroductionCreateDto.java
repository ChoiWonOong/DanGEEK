package DanGEEK.app.dto.member;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberHobby;
import DanGEEK.app.domain.Member.MemberIntroduction;
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
    private List<String> hobbies;
    private String contents;
    public MemberIntroduction toMemberIntroduction(Member member, List<MemberHobby> hobbyList){
        return MemberIntroduction.builder()
                .name(name)
                .major(major)
                .grade(grade)
                .sex(sex)
                .personality(personality)
                .hobbyList(hobbyList)
                .contents(contents)
                .member(member)
                .build();
    }
    public MemberIntroductionCreateDto toIntroductionDto(List<String> hobbies){
        return new MemberIntroductionCreateDto(
                this.name, this.major, this.grade, this.sex, this.personality, hobbies, this.contents
        );
    }

}
