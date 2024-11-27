package DanGEEK.app.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChatRoomMembersDto {
    Long roomId;
    List<String> members;
}
