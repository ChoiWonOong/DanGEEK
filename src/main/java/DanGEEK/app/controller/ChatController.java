package DanGEEK.app.controller;

import DanGEEK.app.dto.chat.ChatRequestDto;
import DanGEEK.app.dto.chat.ChatResponseDto;
import DanGEEK.app.service.ChatRoomMemberService;
import DanGEEK.app.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;
    //private final ChatRoomMemberService chatRoomMemberService;
    // stomp 연결시 토큰 앞에 Bearer 붙이지 않기
    /*@MessageMapping("/chatroom/enter/{chatRoomId}")
    public void enter(@DestinationVariable(value = "chatRoomId") Long chatRoomId, ChatRequestDto chatRequestDto) {
        // 현재 유저의 입장 메시지 채팅 전송, DB에 저장
        chatService.enterChatRoom(chatRoomId, chatRequestDto);
        // 현재 유저와 채팅방 사이의 관계 매핑
        chatRoomMemberService.createChatRoomMember(chatRoomId, chatRequestDto.getSenderId());
    }*/
    /*@MessageMapping("/chatroom/exit/{chatRoomId}")
    public void exit(@DestinationVariable(value = "chatRoomId") Long chatRoomId, ChatRequestDto chatRequestDto) {
        // 현재 유저의 퇴장 메시지 채팅 전송, DB에 저장
        chatService.exitChatRoom(chatRoomId,chatRequestDto);
    }*/
    @MessageMapping("/chatroom/{chatRoomId}")  // (2)
    public void chat(@DestinationVariable(value = "chatRoomId") Long chattingRoomId, ChatRequestDto chattingRequest) throws JsonProcessingException {
        // 현재 채팅방에 메시지 전송, DB에 저장
        chatService.talk(chattingRoomId, chattingRequest);
    }
    @GetMapping("/chatroom/{chatRoomId}")
    public ResponseEntity<?> getChatting(@DestinationVariable(value = "chatRoomId") Long chatRoomId){
        try{
            return ResponseEntity.ok(chatService.findByRoomId(chatRoomId));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
