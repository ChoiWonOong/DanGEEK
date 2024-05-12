package DanGEEK.app.domain;

import DanGEEK.app.dto.NotificationSendDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

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
    public NotificationSendDto toDto(){
        return new NotificationSendDto(post.getId(), sender.getId(), receiver.getId(), readFlag);
    }
}
