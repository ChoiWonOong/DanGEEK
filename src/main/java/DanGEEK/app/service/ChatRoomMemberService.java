package DanGEEK.app.service;

import DanGEEK.app.domain.ChatRoomMember;
import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import DanGEEK.app.repository.ChatRoomMemberRepository;
import DanGEEK.app.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomMemberService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    public ChatRoomMemberCreateResponseDto enterChatRoomMember(Long roomId, Long userId){
        ChatRoomMember chatRoomMember = new ChatRoomMember(roomId, userId);
        chatRoomMemberRepository.save(chatRoomMember);
        return chatRoomMember.toResponseDto();
    }
    //방 나가기
    public void deleteChatRoomMember(Long roomId, Long userId){
        chatRoomMemberRepository.deleteByRoomIdAndUserId(roomId, userId);
        if(chatRoomMemberRepository.findAllByRoomId(roomId).size() == 0){
            chatRoomRepository.deleteById(roomId);
        }
    }
    // 방의 모든 멤버 이름을 출력
    public List<String> findAllMemberNameByRoomId(Long roomId){
        return chatRoomMemberRepository.findAllMemberNameByRoomId(roomId);
    }
}
