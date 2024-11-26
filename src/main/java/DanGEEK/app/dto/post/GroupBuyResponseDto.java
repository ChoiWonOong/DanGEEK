package DanGEEK.app.dto.post;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Post;
import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.util.SecurityUtil;
import lombok.Getter;

@Getter
public class GroupBuyResponseDto extends PostResponseDto{
    public GroupBuyResponseDto(Post post, Member member, ChatRoomResponseDto chatRoomResponseDto) {
        super(post.getId(), member.getId(), post.getTitle(), post.getContents(), member.getNickname(), post.getType().getString(), chatRoomResponseDto);
        this.link = post.getLink();
        this.mallName = post.getMallName();
        this.item = post.getItem();
        this.price = post.getPrice();
        this.imageUrl = post.getImageUrl();
    }
    private String imageUrl;
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
