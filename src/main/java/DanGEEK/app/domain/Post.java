package DanGEEK.app.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "post_type")
    PostType type;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.REMOVE)
    private List<Notification> notifications;
    public Post(String title, String contents, String type) {
        this.title = title;
        this.contents = contents;
        this.type = PostType.valueOf(type);
    }
}
