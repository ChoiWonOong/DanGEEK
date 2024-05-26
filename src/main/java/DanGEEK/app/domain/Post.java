package DanGEEK.app.domain;

import DanGEEK.app.dto.post.ComplainResponseDto;
import DanGEEK.app.dto.post.GroupBuyResponseDto;
import DanGEEK.app.dto.post.MateInviteResponseDto;
import DanGEEK.app.dto.post.PostResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "post_type")
    private PostType type;
    //mate invite field
    @Column(name = "invite_number")
    private Long inviteNumber;
    //group buy field
    private String link;
    private String mallName;
    private String item;
    private String price;
    //complain field
    @Column(name = "room_number")
    private String roomNumber;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Post(String title, String contents, PostType type, Member member) {
        this.title = title;
        this.contents = contents;
        this.type = type;
        this.member = member;
    }
    public PostResponseDto toResponseDto() {
        if (type == PostType.GROUP_BUY) {
            return new GroupBuyResponseDto(id, title, contents, member.getNickname(), type.getType());
        } else if (type == PostType.INVITE) {
            return new MateInviteResponseDto(id, title, contents, member.getNickname(), type.getType());
        } else
            return new ComplainResponseDto(id, title, contents, member.getNickname(), type.getType());
    }
    public Member getMember() {
        return member;
    }
}
