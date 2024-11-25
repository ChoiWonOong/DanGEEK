package DanGEEK.app.domain.Member;

import DanGEEK.app.domain.BaseEntity;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import DanGEEK.app.util.StringListConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class MemberIntroduction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="major", nullable = false)
    private String major;
    @Column(name="grade", nullable = false)
    private String grade;
    @Column(name="sex", nullable = false)
    private String sex;
    @Column(name = "contents", nullable = true)
    private String contents;
    @Column(name="personality", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> personality = new ArrayList<>();
    @OneToMany
    @JoinColumn(name="hobbies")
    private List<MemberHobby> hobbies = new ArrayList<>();
    @Builder
    public MemberIntroduction(String name, String major, String grade, String sex, String contents, List<String> personality, List<MemberHobby> hobbyList, Member member){
        this.name = name;
        this.major = major;
        this.grade = grade;
        this.sex = sex;
        this.personality = personality;
        this.hobbies = hobbyList;
        this.member = member;
        this.contents = contents;
    }

    public MemberIntroductionCreateDto toIntroductionDto() {
        return new MemberIntroductionCreateDto(name, major, grade, sex, personality, getHobbyList(), contents);
    }
    public List<String> getHobbyList(){
        return hobbies.stream().map(h->h.getHobby().getName()).toList();
    }
}
