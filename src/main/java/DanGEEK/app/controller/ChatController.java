package DanGEEK.app.controller;

import DanGEEK.app.dto.chat.ChatRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;  // (1)
    // stomp 연결시 토큰 앞에 Bearer 붙이지 않기
    @MessageMapping("/chatroom/{chatRoomId}")  // (2)
    public void chat(@DestinationVariable(value = "chatRoomId") Long chattingRoomId, ChatRequestDto chattingRequest) {  // (3)
        log.info("Messaging Start");
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + chattingRoomId, chattingRequest);
        log.info("Message [{}] send by member: {} to chat room: {}", chattingRequest.getMessage(), chattingRequest.getSender(), chattingRoomId);
    }
}