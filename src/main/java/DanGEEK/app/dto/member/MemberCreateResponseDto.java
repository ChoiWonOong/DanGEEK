package DanGEEK.app.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateResponseDto {
    private String username;
    private String nickname;
    private String imageUrl = null;

    public MemberCreateResponseDto(String username, String name) {
        this.username = username;
        this.nickname = name;
    }
}