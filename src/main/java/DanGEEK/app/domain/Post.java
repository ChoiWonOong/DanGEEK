package DanGEEK.app.domain;

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
    PostType type;

    public Post(String title, String contents, String type) {
        this.title = title;
        this.contents = contents;
        this.type = PostType.valueOf(type);
    }
}
