package DanGEEK.app.dto;

import lombok.Getter;

@Getter
public class ChatDto {
    private String type;
    private String roomId;
    private String sender;
    private String message;
}
