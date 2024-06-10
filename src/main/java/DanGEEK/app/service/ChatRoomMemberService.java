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
    private final MemberRepository memberRepository;
    public ChatRoomMember createChatRoomMember(Long roomId, Long senderId){
        Member member= memberRepository.findById(senderId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        ChatRoomMember chatRoomMember = new ChatRoomMember(member, chatRoom);
        chatRoomMemberRepository.save(chatRoomMember);
        return chatRoomMember;
    }
    //방 나가기
    public void deleteChatRoomMember(Long roomId, Long senderId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Member member = memberRepository.findById(senderId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        chatRoomMemberRepository.deleteAllByRoomIdAndUserId(chatRoom, member);
        if(chatRoomMemberRepository.findAllByRoomId(chatRoom).isEmpty()){
            chatRoomRepository.deleteById(roomId);
        }
    }
    // 방의 모든 멤버 이름을 출력
    public List<String> findAllMemberNameByRoomId(Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<Member> members = chatRoomMemberRepository.findAllMemberByRoomId(chatRoom);
        return members.stream().map(Member::getNickname).toList();
    }
}
