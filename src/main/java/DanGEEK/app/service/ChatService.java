package DanGEEK.app.service;

import DanGEEK.app.domain.Chat.Chat;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.MessageType;
import DanGEEK.app.dto.FlaskDto;
import DanGEEK.app.dto.chat.ChatRequestDto;
import DanGEEK.app.dto.chat.ChatResponseDto;
import DanGEEK.app.repository.ChatRepository;
import DanGEEK.app.repository.ChatRoomMemberRepository;
import DanGEEK.app.repository.ChatRoomRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRepository chatRepository;
    private final FlaskService flaskService;
    private final MemberService memberService;
    public ChatResponseDto talk(Long roomId,ChatRequestDto chatDto) throws JsonProcessingException, ParseException {
        Member member = memberService.getMe();
        String nickname = member.getNickname();
        chatDto.setRoomId(roomId);
        log.info("chatDto : {}",chatDto);
        boolean isBadWords = flaskService.checkBadWords(new FlaskDto(chatDto.getMessage()));
        Chat chat = chatDto.toEntity(MessageType.TALK);
        if(isBadWords){
            chat.setBadWords();
        }
        chat.setSenderId(member.getId());
        chat.setSenderNickname(nickname);
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        return chatRepository.save(chat).toResponseDto();
    }
    public void enterChatRoom(Long roomId, ChatRequestDto chatDto){
        Member member = memberService.getMe();//ChatRoomMember chatRoomMember = ;
        String nickname = member.getNickname();
        chatDto.setRoomId(roomId);
        chatDto.setMessage(nickname+MessageType.ENTER.message);
        Chat chat = chatDto.toEntity(MessageType.ENTER);
        chat.setSenderNickname(member.getNickname());
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        chatRepository.save(chat);
    }
    public void exitChatRoom(Long roomId, ChatRequestDto chatDto){
        Member member = memberService.getMe();
        String nickname = member.getNickname();chatDto.setRoomId(roomId);
        chatDto.setMessage(nickname+MessageType.EXIT.message);
        Chat chat = chatDto.toEntity(MessageType.EXIT);
        chat.setSenderNickname(member.getNickname());
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        chatRepository.save(chat);
    }
    public List<ChatResponseDto> findByRoomId(Long roomId){
        return Chat.toDtoList(chatRepository.findAllByRoomId(roomId));
    }
    public void deleteChatRoom(Long roomId){
        chatRepository.deleteAllByRoomId(roomId);
    }
}
