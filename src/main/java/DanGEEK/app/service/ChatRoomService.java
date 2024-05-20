package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.ChatRoom;
import DanGEEK.app.domain.ChatRoomMember;
import DanGEEK.app.dto.chat.ChatRoomCreateDto;
import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.repository.ChatRoomMemberRepository;
import DanGEEK.app.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    public List<ChatRoomResponseDto> findAllRooms(){
        return chatRoomRepository.findAll().stream().map(ChatRoom::toResponseDto).toList();
    }
    public ChatRoom findRoomById(Long roomId){
        return chatRoomRepository.findById(roomId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public List<ChatRoomResponseDto> findChatroomByUserId(Long id){
        List<ChatRoomMember> myChatRoomMembers = chatRoomMemberRepository.findAllByUserId(id);
        if(myChatRoomMembers.size() == 0){
            throw new RestApiException(ErrorCode.NOT_EXIST_ERROR);
        }
        List<Long> myChatRoomIds = new ArrayList<>();
        for(ChatRoomMember m : myChatRoomMembers){
            myChatRoomIds.add(m.getRoomId());
        }
        List<ChatRoom> myChatRooms = chatRoomRepository.findAllById(myChatRoomIds);
        return myChatRooms.stream().map(ChatRoom::toResponseDto).toList();
    }
    public ChatRoomResponseDto createChatRoom(ChatRoomCreateDto chatRoomCreateDto){
        ChatRoom chatRoom = new ChatRoom(chatRoomCreateDto.getName());
        chatRoomRepository.save(chatRoom);
        return chatRoom.toResponseDto();
    }
    public void deleteChatRoom(Long roomId){
        chatRoomRepository.deleteById(roomId);
    }

}
