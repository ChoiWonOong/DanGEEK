package DanGEEK.app.domain.Chat;

import DanGEEK.app.dto.chat.ChatRoomCreateDto;
import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class ChatRoom {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @Setter
    @Getter
    private String name;

    @Getter
    private int currentUsers = 0;
    @Getter
    @Setter
    private int maxUser = 2;
    public static List<ChatRoomCreateDto> toDtoList(List<ChatRoom> chatRoomList){
        List<ChatRoomCreateDto> roomDtos = new ArrayList<>();
        for(ChatRoom r : chatRoomList){
            ChatRoomCreateDto dto = new ChatRoomCreateDto(r.name, r.maxUser);
            roomDtos.add(dto);
        }
        return roomDtos;
    }

    public ChatRoom(String name, int maxUser) {
        this.name = name;
        this.maxUser = maxUser;
    }
    public ChatRoomResponseDto toResponseDto(){
        return new ChatRoomResponseDto(roomId, name, currentUsers, maxUser);
    }
    public void enterRoom(){
        currentUsers++;
    }
    public void exitRoom(){
        currentUsers--;
    }
}
