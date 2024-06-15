package DanGEEK.app.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NicknameCheckDto {
    private String nickname;

    private boolean isExist;

    public void setExist(boolean exist) {
        isExist = exist;
    }
}
