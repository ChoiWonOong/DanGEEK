package DanGEEK.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationSendDto {
    private Long post_id;
    private Long sender_id;
    private Long receiver_id;
    private Boolean read_flag;
}
