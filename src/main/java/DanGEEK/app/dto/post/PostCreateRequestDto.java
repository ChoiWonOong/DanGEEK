package DanGEEK.app.dto.post;

import DanGEEK.app.domain.PostType;
import lombok.Getter;

@Getter
public class PostCreateRequestDto {
    //base field
    private String title;
    private String contents;
    private Long userId;
    private PostType post_type;
    private String imageUrl;
    //invite, group buy field
    private Long inviteNumber;
    //group buy field
    private String link;
    private String mallName;
    private String item;
    private String price;
    //complain field
    private String roomNumber;

    public void setPost_type(PostType post_type) {
        this.post_type = post_type;
    }
    public PostCreateRequestDto(String title, String content, Long userId) {
        this.title = title;
        this.contents = content;
        this.userId = userId;
    }
}
