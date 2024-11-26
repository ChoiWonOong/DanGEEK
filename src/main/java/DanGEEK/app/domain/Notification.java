/*
package DanGEEK.app.domain;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.Notification.GroupBuyNotificationSendDto;
import DanGEEK.app.dto.Notification.MateNotificationSendDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Notification {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;


    @Getter
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @Column(name="read_flag")
    private Boolean readFlag;

    public Notification(Post post, Member sender, Member receiver ) {
        this.post = post;
        this.sender = sender;
        this.receiver = receiver;
        this.readFlag = false;
    }
    public MateNotificationSendDto toMateNotificationDto(){
        return new MateNotificationSendDto(post.getId(), sender.getNickname(), receiver.getNickname(), readFlag);
    }
    public GroupBuyNotificationSendDto toGroupBuyNotificationDto(){
        return new GroupBuyNotificationSendDto(post.getId(), sender.getNickname(), receiver.getNickname(), readFlag);
    }

}
*/
