package DanGEEK.app.dto.post;

import DanGEEK.app.domain.PostType;
import lombok.Getter;

@Getter
public class GroupBuyResponseDto extends PostResponseDto{
    public GroupBuyResponseDto(Long post_id, String title, String contents, String nickname, PostType post_type) {
        super(post_id, title, contents, nickname, post_type);
    }
}
