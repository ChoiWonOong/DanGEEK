package DanGEEK.app.dto.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class PostCreateRequestDto {
    //base field
    private String title;
    private String contents;
    private Long userId;
    @Setter
    private String post_type;
    //invite, group buy field
    private Long inviteNumber;
    //group buy field
    private String link;
    private String mallName;
    private String item;
    private String price;
    //complain field
    private String dormitoryName;
    private String roomNumber;
    //post update field
    private Long postId;
    private int maxUser;

    public PostCreateRequestDto(String title, String content, Long userId) {
        this.title = title;
        this.contents = content;
        this.userId = userId;
    }
}
