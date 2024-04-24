package DanGEEK.app.dto;

import DanGEEK.app.domain.Authority;
import DanGEEK.app.domain.Member;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Getter
public class MemberCreateRequestDto {
    String username;
    String userEmail;
    String password;
    String nickname;
    @Transactional
    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(username)
                .email(userEmail)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }
    public UsernamePasswordAuthenticationToken toAuthentication() {
        UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(userEmail, password);
        return token;
    }
}
