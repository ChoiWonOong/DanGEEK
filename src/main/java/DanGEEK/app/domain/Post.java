package DanGEEK.app.domain;

import DanGEEK.app.dto.PostDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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
    public PostDto toDto(){
        return new PostDto(id, title, contents, member.getId(), type);
    }
    public Member getMember() {
        return member;
    }
}
