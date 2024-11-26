package DanGEEK.app.dto.post;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Post;
import DanGEEK.app.util.SecurityUtil;
import lombok.Getter;

@Getter
public class ComplainResponseDto extends PostResponseDto{
    public ComplainResponseDto(Post post, Member member) {
        super(post.getId(), member.getId(), post.getTitle(), post.getContents(), post.getTitle(), post.getType().getString(), null);
        this.dormitoryName = post.getDormitoryName();
        this.roomNumber = post.getDormRoomNumber();
    }
    private String dormitoryName;
    private String roomNumber;
}
