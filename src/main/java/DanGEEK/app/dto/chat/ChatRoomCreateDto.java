package DanGEEK.app.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomCreateDto {
    private String name;
    private int maxUser;
}
