package DanGEEK.app.dto.chat;

import lombok.Getter;

@Getter
public class ChatRoomResponseDto {
    private Long id;
    private String name;

    public ChatRoomResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
