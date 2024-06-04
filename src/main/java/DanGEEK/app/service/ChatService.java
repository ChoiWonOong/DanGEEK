package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.*;
import DanGEEK.app.dto.chat.ChatRequestDto;
import DanGEEK.app.dto.chat.ChatResponseDto;
import DanGEEK.app.repository.ChatRepository;
import DanGEEK.app.repository.ChatRoomMemberRepository;
import DanGEEK.app.repository.ChatRoomRepository;
import DanGEEK.app.repository.MemberRepository;
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
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    public void talk(Long roomId,ChatRequestDto chatDto) {

        Member member = memberRepository.findById(chatDto.getSenderId()).get();
        String nickname = member.getNickname();
        chatDto.setRoomId(roomId);
        Chat chat = chatDto.toEntity(MessageType.TALK);
        chat.setSenderNickname(member.getNickname());
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        chatRepository.save(chat);
    }
    public void enterChatRoom(Long roomId, ChatRequestDto chatDto){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Member member = memberRepository.findById(chatDto.getSenderId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        //ChatRoomMember chatRoomMember = ;
        String nickname = member.getNickname();
        chatDto.setRoomId(roomId);
        chatDto.setMessage(nickname+MessageType.ENTER.message);
        Chat chat = chatDto.toEntity(MessageType.ENTER);
        chat.setSenderNickname(member.getNickname());
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        chatRepository.save(chat);
    }
    public void exitChatRoom(Long roomId, ChatRequestDto chatDto){
        Member member = memberRepository.findById(chatDto.getSenderId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        String nickname = member.getNickname();chatDto.setRoomId(roomId);
        chatDto.setMessage(nickname+MessageType.EXIT.message);
        Chat chat = chatDto.toEntity(MessageType.EXIT);
        chat.setSenderNickname(member.getNickname());
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + roomId, chat.toResponseDto());
        chatRepository.save(chat);
    }
    public List<ChatResponseDto> findAllByRoomId(Long roomId){
        return Chat.toDtoList(chatRepository.findAllByRoomId(roomId));
    }
}
