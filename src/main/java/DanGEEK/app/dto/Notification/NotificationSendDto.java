package DanGEEK.app.dto.Notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class NotificationSendDto {
    private Long post_id;
    private String sender;
    private String receiver;
    private Boolean read_flag;
}
