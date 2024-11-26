package DanGEEK.app.domain.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMemberId implements Serializable {
    private Long userId;
    private Long roomId;
}
