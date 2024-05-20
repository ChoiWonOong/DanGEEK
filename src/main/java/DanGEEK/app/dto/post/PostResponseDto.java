package DanGEEK.app.dto.post;

import DanGEEK.app.domain.PostType;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long post_id;
    private String title;
    private String contents;
    private Long member_id;
    private PostType post_type;

    public PostResponseDto(Long post_id, String title, String contents, Long member_id, PostType post_type) {
        this.post_id = post_id;
        this.title = title;
        this.contents = contents;
        this.member_id = member_id;
        this.post_type = post_type;
    }
}
