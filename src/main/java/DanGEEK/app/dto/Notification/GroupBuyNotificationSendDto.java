package DanGEEK.app.dto.Notification;

import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class GroupBuyNotificationSendDto extends NotificationSendDto {
    public GroupBuyNotificationSendDto(Long post_id, String sender, String receiver, Boolean read_flag) {
        super(post_id, sender, receiver, read_flag);
    }
}
