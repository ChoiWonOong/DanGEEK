package DanGEEK.app.domain.Member;

import DanGEEK.app.domain.Hobby;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberHobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    private Hobby hobby;

    public MemberHobby(Member member, Hobby hobby) {
        this.member = member;
        this.hobby = hobby;
    }
}
