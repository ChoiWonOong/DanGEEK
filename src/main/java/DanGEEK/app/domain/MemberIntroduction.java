package DanGEEK.app.domain;

import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import DanGEEK.app.util.StringListConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class MemberIntroduction extends BaseEntity{
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
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name="personality", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> personality;
    @Column(name="hobby", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> hobby;

    @Builder
    public MemberIntroduction(String name, String major, String grade, String sex, String contents, List<String> personality, List<String> hobby, Member member){
        this.name = name;
        this.major = major;
        this.grade = grade;
        this.sex = sex;
        this.personality = personality;
        this.hobby = hobby;
        this.member = member;
        this.contents = contents;
    }
    public MemberIntroductionCreateDto toIntroductionDto(){
        return new MemberIntroductionCreateDto(
                this.name, this.major, this.grade, this.sex, this.personality, this.hobby, this.contents
        );
    }
}
