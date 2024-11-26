package DanGEEK.app.dto.post;

import lombok.Getter;

@Getter
public class PostDeleteDto {
    private Long postId;
    private Long chatRoomId;
    String status = "FAIL";
    public PostDeleteDto(Long postId) {
        this.postId = postId;
        this.status = "SUCCESS";
    }
    public PostDeleteDto(Long postId, Long chatRoomId) {
        this.postId = postId;
        this.chatRoomId = chatRoomId;
        this.status = "SUCCESS";
    }

}
