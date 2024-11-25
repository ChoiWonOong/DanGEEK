package DanGEEK.app.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberPasswordReassignDto {
    String username;
    String password;
    Boolean status = false;

    public MemberPasswordReassignDto(String username, Boolean status) {
        this.username = username;
        this.status = status;
    }
}
