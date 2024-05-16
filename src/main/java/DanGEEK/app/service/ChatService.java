package DanGEEK.app.service;

import DanGEEK.app.domain.Chat;
import DanGEEK.app.domain.MessageType;
import DanGEEK.app.dto.chat.ChatRequestDto;
import DanGEEK.app.dto.chat.ChatResponseDto;
import DanGEEK.app.repository.ChatRepository;
import DanGEEK.app.repository.MemberRepository;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    public void talk(Long roomId,ChatRequestDto chatDto) {
        Chat chat = chatDto.toEntity(MessageType.TALK);
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chatDto);
        chatRepository.save(chat);
    }
    public void enterChatRoom(Long roomId){
        ChatRequestDto chatDto = new ChatRequestDto(roomId, memberRepository.findById(SecurityUtil.getCurrentMemberId())+MessageType.ENTER.message, "system");
        Chat chat = chatDto.toEntity(MessageType.ENTER);
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId);
        chatRepository.save(chat);
    }
    public void exitChatRoom(Long roomId){
        ChatRequestDto chatDto = new ChatRequestDto(roomId, memberRepository.findById(SecurityUtil.getCurrentMemberId())+MessageType.EXIT.message, "system");
        Chat chat = chatDto.toEntity(MessageType.EXIT);
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId);
        chatRepository.save(chat);

    }
    public List<ChatResponseDto> findAllByRoomId(Long roomId){
        return Chat.toDtoList(chatRepository.findAllByRoomId(roomId));
    }
}
