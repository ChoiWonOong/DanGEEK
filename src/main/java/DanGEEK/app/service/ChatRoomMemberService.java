package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.ChatRoomMember;
import DanGEEK.app.domain.Member;
import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
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
    public ChatRoomMemberCreateResponseDto enterChatRoomMember(Long roomId, String sender){
        Member member= memberRepository.findByNickname(sender).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        ChatRoomMember chatRoomMember = new ChatRoomMember(roomId, member.getId());
        chatRoomMemberRepository.save(chatRoomMember);
        return chatRoomMember.toResponseDto();
    }
    //방 나가기
    public void deleteChatRoomMember(Long roomId, String sender){
        Member member= memberRepository.findByNickname(sender).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        chatRoomMemberRepository.deleteByRoomIdAndUserId(roomId, member.getId());
        if(chatRoomMemberRepository.findAllByRoomId(roomId).size() == 0){
            chatRoomRepository.deleteById(roomId);
        }
    }
    // 방의 모든 멤버 이름을 출력
    public List<String> findAllMemberNameByRoomId(Long roomId){
        return chatRoomMemberRepository.findAllMemberNameByRoomId(roomId);
    }
}
