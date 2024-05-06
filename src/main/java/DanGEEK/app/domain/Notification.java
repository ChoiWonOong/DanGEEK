package DanGEEK.app.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name="read_flag")
    private Boolean readFlag;

    public Notification(Post post, Member member) {
        this.post = post;
        this.member = member;
        this.readFlag = false;
    }
}
