package DanGEEK.app.dto.Notification;

import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateNotificationSendDto extends NotificationSendDto{
    private MemberIntroductionCreateDto memberIntroductionCreateDto;

    public MateNotificationSendDto(Long post_id, String sender, String receiver, Boolean read_flag) {
        super(post_id, sender, receiver, read_flag);
    }

    public void setMemberIntroductionCreateDto(MemberIntroductionCreateDto memberIntroductionCreateDto) {
        this.memberIntroductionCreateDto = memberIntroductionCreateDto;
    }
}
