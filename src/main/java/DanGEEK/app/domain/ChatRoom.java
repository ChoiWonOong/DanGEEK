package DanGEEK.app.domain;

import DanGEEK.app.dto.chat.ChatRoomCreateDto;
import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    public ChatRoomResponseDto toResponseDto(){
        return new ChatRoomResponseDto(roomId, name);
    }
}
