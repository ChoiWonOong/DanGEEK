package DanGEEK.app.dto.post;

import DanGEEK.app.domain.PostType;
import DanGEEK.app.util.SecurityUtil;
import lombok.Getter;

@Getter
public class ComplainResponseDto extends PostResponseDto{
    public ComplainResponseDto(Long post_id, String title, String contents, String nickname, String post_type) {
        super(post_id, SecurityUtil.getCurrentMemberId(), title, contents, nickname, post_type);
    }
    private String roomNumber;
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
