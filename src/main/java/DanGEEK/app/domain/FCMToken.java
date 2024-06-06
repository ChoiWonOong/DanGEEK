package DanGEEK.app.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class FCMToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Member member;
    private String token;
    @Builder
    public FCMToken(Member member, String token){
        this.member = member;
        this.token = token;
    }
    public void updateToken(String token){
        this.token = token;
    }
}
