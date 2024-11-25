package DanGEEK.app.domain.Member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "similarity")
public class Similarity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Member> members;
    @Getter
    private double similarity;
    public Similarity(Member member1, Member member2, double similarity) {
        this.members = List.of(member1, member2);
        this.similarity = similarity;
    }
    public Member getOtherMember(Member member){
        return members.stream().filter(m -> !m.equals(member)).findFirst().orElse(null);
    }
}
