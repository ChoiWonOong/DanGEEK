package DanGEEK.app.dto.post;

import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.util.SecurityUtil;
import lombok.Getter;

@Getter
public class GroupBuyResponseDto extends PostResponseDto{
    public GroupBuyResponseDto(Long post_id, String title, String contents, String nickname, String post_type, String link, String mallName, String item, String price ,ChatRoomResponseDto chatRoomResponseDto) {
        super(post_id, SecurityUtil.getCurrentMemberId(), title, contents, nickname, post_type);
    }
    private String link;
    private String mallName;
    private String item;
    private String price;

    public void setGroupBuyInfo(String link, String mallName, String item, String price) {
        this.link = link;
        this.mallName = mallName;
        this.item = item;
        this.price = price;
    }
}
