package DanGEEK.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginDto {
    @NotNull
    String userEmail;
    @NotNull
    String password;
    public UsernamePasswordAuthenticationToken toAuthentication() {
        UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(userEmail, password);
        System.out.println("email : "+ userEmail + "\nprincipal : " + token.getPrincipal());
        return token;
    }
}
