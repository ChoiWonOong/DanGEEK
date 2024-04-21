package DanGEEK.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class MemberCreateResponseDto {
    private String username;
    private String userEmail;
    private String nickname;

    public MemberCreateResponseDto(String username, String userEmail, String name) {
        this.username = username;
        this.userEmail = userEmail;
        this.nickname = name;
    }
}