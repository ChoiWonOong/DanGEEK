package DanGEEK.app.dto;

import lombok.Getter;

@Getter
public class PostDto {
    private String title;
    private String contents;
    private String post_type;

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }
}
