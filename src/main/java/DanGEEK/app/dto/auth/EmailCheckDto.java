package DanGEEK.app.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
public class EmailCheckDto {
    private String email;
    @Setter
    private boolean isExist;
}
