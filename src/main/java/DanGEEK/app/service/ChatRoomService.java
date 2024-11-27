package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Chat.ChatRoom;
import DanGEEK.app.domain.Chat.ChatRoomMember;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.repository.ChatRoomMemberRepository;
import DanGEEK.app.repository.ChatRoomRepository;
import DanGEEK.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatService chatService;
    private final ChatRoomMemberService chatRoomMember;
    public List<ChatRoomResponseDto> findAllRooms(){
        return chatRoomRepository.findAll().stream().map(ChatRoom::toResponseDto).toList();
    }
    public ChatRoom findRoomById(Long roomId){
        return chatRoomRepository.findById(roomId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public List<ChatRoomResponseDto> findChatroomByUserId(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<ChatRoomMember> myChatRoomMembers = chatRoomMemberRepository.findAllByUserId(member);
        List<ChatRoom> myChatRooms = new ArrayList<>();
        for(ChatRoomMember m : myChatRoomMembers){
            myChatRooms.add(m.getRoomId());
        }
        return myChatRooms.stream().map(ChatRoom::toResponseDto).toList();
    }
    public ChatRoom createChatRoom(String name, int maxUser){
        ChatRoom chatRoom = new ChatRoom(name, maxUser);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }
    public void deleteChatRoom(Long roomId){
        chatRoomRepository.deleteById(roomId);
    }
    public void deleteChatRoomMember(Long roomId){
        chatRoomMember.deleteChatRoomMember(roomId);
        deleteChatRoom(roomId);
        chatService.deleteChatRoom(roomId);
    }
}
