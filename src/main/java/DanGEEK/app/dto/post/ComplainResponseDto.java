package DanGEEK.app.dto.post;

import DanGEEK.app.util.SecurityUtil;
import lombok.Getter;

@Getter
public class ComplainResponseDto extends PostResponseDto{
    public ComplainResponseDto(Long post_id, String title, String contents, String nickname, String post_type, String dormitoryName, String roomNumber) {
        super(post_id, SecurityUtil.getCurrentMemberId(), title, contents, nickname, post_type, null);
        this.dormitoryName = dormitoryName;
        this.roomNumber = roomNumber;
    }
    private String dormitoryName;
    private String roomNumber;
}
