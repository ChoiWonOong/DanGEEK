package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.ChatRoom;
import DanGEEK.app.domain.ChatRoomMember;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.repository.ChatRoomMemberRepository;
import DanGEEK.app.repository.ChatRoomRepository;
import DanGEEK.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomMemberService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final MemberService memberService;

    public ChatRoomMember createChatRoomMember(Long roomId, Long senderId){
        Member member= memberService.findMemberById(senderId);
        ChatRoom chatRoom = findRoomById(roomId);
        chatRoom.enterRoom();
        return chatRoomMemberRepository.save(new ChatRoomMember(member, chatRoom));
    }
    //방 나가기
    public void deleteChatRoomMember(Long roomId, Long senderId){
        ChatRoom chatRoom = findRoomById(roomId);
        Member member = memberService.findMemberById(senderId);
        chatRoomMemberRepository.deleteAllByRoomIdAndUserId(chatRoom, member);
        chatRoom.exitRoom();
        //if(chatRoomMemberRepository.findAllByRoomId(chatRoom).isEmpty()){
        if(chatRoom.getCurrentUsers()==0){
            chatRoomRepository.deleteById(roomId);
        }
    }
    // 방의 모든 멤버 이름을 출력
    public List<String> findAllMemberNameByRoomId(Long roomId){
        List<Member> members = findAllMemberByRoomId(roomId);
        return members.stream().map(Member::getNickname).toList();
    }
    public ChatRoom findRoomById(Long roomId){
        return chatRoomRepository.findById(roomId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public List<Member> findAllMemberByRoomId(Long roomId){
        ChatRoom chatRoom = findRoomById(roomId);
        return chatRoomMemberRepository.findAllMemberByRoomId(chatRoom);
    }
}
