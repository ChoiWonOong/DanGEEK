package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.ChatRoom;
import DanGEEK.app.dto.ChatRoomCreateDto;
import DanGEEK.app.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomCreateDto> findAllRooms(){
        return ChatRoom.toDtoList(chatRoomRepository.findAll());
    }
    public ChatRoom findRoomById(Long roomId){
        return chatRoomRepository.findById(roomId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public ChatRoom createChatRoom(ChatRoomCreateDto chatRoomCreateDto){
        ChatRoom chatRoom = new ChatRoom(chatRoomCreateDto.getName());
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }
}