package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Chat.ChatRoom;
import DanGEEK.app.domain.Chat.ChatRoomMember;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.chat.ChatRequestDto;
import DanGEEK.app.dto.chat.ChatRoomMemberDeleteDto;
import DanGEEK.app.dto.chat.ChatRoomMembersDto;
import DanGEEK.app.repository.ChatRoomMemberRepository;
import DanGEEK.app.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomMemberService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final MemberService memberService;
    private final ChatService chatService;
    @Transactional
    // 방 입장
    public ChatRoomMember createChatRoomMember(Long roomId){
        Member member= memberService.getMe();
        // 방 찾기
        ChatRoom chatRoom = findRoomById(roomId);
        // chatRoom.currentUsers++;
        chatRoom.enterRoom();
        // 채팅방 입장 글 생성 및 저장
        chatService.enterChatRoom(roomId, new ChatRequestDto(member.getId()));
        return chatRoomMemberRepository.save(new ChatRoomMember(member, chatRoom));
    }
    @Transactional
    //방 나가기
    public ChatRoomMemberDeleteDto deleteChatRoomMember(Long roomId){
        Member member = memberService.getMe();
        ChatRoom chatRoom = findRoomById(roomId);
        chatRoomMemberRepository.deleteAllByRoomIdAndUserId(chatRoom, member);
        chatRoom.exitRoom();
        chatService.exitChatRoom(roomId, new ChatRequestDto(member.getId()));
        if(chatRoom.getCurrentUsers()==0){
            chatRoomRepository.deleteById(roomId);
        }
        return new ChatRoomMemberDeleteDto(roomId, member.getId());
    }
    // 방의 모든 멤버 이름을 출력
    public ChatRoomMembersDto findAllMemberNameByRoomId(Long roomId){
        List<Member> members = findAllMemberByRoomId(roomId);
        List<String> str_members = members.stream().map(Member::getNickname).toList();
        return new ChatRoomMembersDto(roomId, str_members);
    }
    public ChatRoom findRoomById(Long roomId){
        return chatRoomRepository.findById(roomId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public List<Member> findAllMemberByRoomId(Long roomId){
        ChatRoom chatRoom = findRoomById(roomId);
        List<Member> members = chatRoomMemberRepository.findAllMemberByRoomId(chatRoom).stream().map(ChatRoomMember::getUserId).toList();
        return members;
    }
}
