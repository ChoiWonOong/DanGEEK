package DanGEEK.app.dto.chat;

import lombok.Getter;

@Getter
public class ChatRoomResponseDto {
    private Long id;
    private String name;
    private int currentUsers;
    private int maxUser;
    public ChatRoomResponseDto(Long id, String name, int currentUsers, int maxUser) {
        this.id = id;
        this.name = name;
        this.maxUser = maxUser;
        this.currentUsers = currentUsers;
    }
}
