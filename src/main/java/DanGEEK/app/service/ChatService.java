package DanGEEK.app.service;

import DanGEEK.app.domain.Chat;
import DanGEEK.app.dto.chat.ChatRequestDto;
import DanGEEK.app.dto.chat.ChatResponseDto;
import DanGEEK.app.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public void save(ChatRequestDto chatDto) {
        Chat chat = chatDto.toEntity();
        chatRepository.save(chat);
    }
    public List<ChatResponseDto> findAllByRoomId(Long roomId){
        return Chat.toDtoList(chatRepository.findAllByRoomId(roomId));
    }
}
