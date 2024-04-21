package DanGEEK.app.domain;

import DanGEEK.app.dto.MemberCreateResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {
    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Authority authority;
    @Builder
    public Member(String username, String email, String password, String nickname, Authority authority){
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
    }
    public static MemberCreateResponseDto memberToResponseDto(Member member) {
        return new MemberCreateResponseDto(member.username, member.email, member.nickname);
    }
    public static User memberToUser(Member member){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.authority.toString());
        return new User(
                String.valueOf(member.id),
                member.password,
                Collections.singleton(grantedAuthority)
        );
    }
}
