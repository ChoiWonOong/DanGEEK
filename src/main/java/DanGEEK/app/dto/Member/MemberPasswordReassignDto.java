package DanGEEK.app.dto.Member;

import lombok.Getter;

@Getter
public class MemberPasswordReassignDto {
    String username;
    String password;
    Boolean status;

    public MemberPasswordReassignDto(String username, Boolean status) {
        this.username = username;
        this.status = status;
    }
}