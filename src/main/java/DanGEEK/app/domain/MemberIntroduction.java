package DanGEEK.app.domain;

import DanGEEK.app.util.StringListConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class MemberIntroduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="major", nullable = false)
    private String major;
    @Column(name="grade", nullable = false)
    private String grade;
    @Column(name="sex", nullable = false)
    private String sex;
    @Column(name="personality", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> personality;
    @Column(name="hobby", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> hobby;

    @Builder
    public MemberIntroduction(String name, String major, String grade, String sex, List<String> personality, List<String> hobby){
        this.name = name;
        this.major = major;
        this.grade = grade;
        this.sex = sex;
        this.personality = personality;
        this.hobby = hobby;
    }
}
