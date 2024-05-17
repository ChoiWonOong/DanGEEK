package DanGEEK.app.service;

import DanGEEK.app.domain.Chat;
import DanGEEK.app.domain.MessageType;
import DanGEEK.app.dto.chat.ChatRequestDto;
import DanGEEK.app.dto.chat.ChatResponseDto;
import DanGEEK.app.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRepository chatRepository;
    public void talk(Long roomId,ChatRequestDto chatDto) {
        chatDto.setRoomId(roomId);
        Chat chat = chatDto.toEntity(MessageType.TALK);
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        chatRepository.save(chat);
    }
    public void enterChatRoom(Long roomId, ChatRequestDto chatDto){
        chatDto.setRoomId(roomId);
        chatDto.setMessage(chatDto.getSender()+MessageType.ENTER.message);
        Chat chat = chatDto.toEntity(MessageType.ENTER);
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        chatRepository.save(chat);
    }
    public void exitChatRoom(Long roomId, ChatRequestDto chatDto){
        chatDto.setRoomId(roomId);
        chatDto.setMessage(chatDto.getSender()+MessageType.EXIT.message);
        Chat chat = chatDto.toEntity(MessageType.EXIT);
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        chatRepository.save(chat);
    }
    public List<ChatResponseDto> findAllByRoomId(Long roomId){
        return Chat.toDtoList(chatRepository.findAllByRoomId(roomId));
    }
}
