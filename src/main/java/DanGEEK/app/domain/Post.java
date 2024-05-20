package DanGEEK.app.domain;

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
            return new GroupBuyResponseDto(id, title, contents, member.getNickname(), type);
        } else {
            return new MateInviteResponseDto(id, title, contents, member.getNickname(), type);
        }
    }
    public Member getMember() {
        return member;
    }
}
