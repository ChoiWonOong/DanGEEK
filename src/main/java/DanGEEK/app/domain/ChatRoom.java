package DanGEEK.app.domain;

import DanGEEK.app.dto.ChatRoomCreateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String name;
    public static List<ChatRoomCreateDto> toDtoList(List<ChatRoom> chatRoomList){
        List<ChatRoomCreateDto> roomDtos = new ArrayList<>();
        for(ChatRoom r : chatRoomList){
            ChatRoomCreateDto dto = new ChatRoomCreateDto(r.name);
            roomDtos.add(dto);
        }
        return roomDtos;
    }

    public ChatRoom(String name) {
        this.name = name;
    }
}
