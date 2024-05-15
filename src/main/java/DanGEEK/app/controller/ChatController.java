package DanGEEK.app.controller;

import DanGEEK.app.domain.ChatMessage;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;  // (1)

    @MessageMapping("/chattings/{chattingRoomId}")  // (2)
    public void chat(@DestinationVariable Long chattingRoomId, ChatMessage chattingRequest) {  // (3)
        log.info("Messaging Start");
        simpMessagingTemplate.convertAndSend("/subscription/chattings/" + chattingRoomId, chattingRequest.getMessage());
        log.info("Message [{}] send by member: {} to chatting room: {}", chattingRequest.getMessage(), chattingRequest.getSender(), chattingRoomId);
    }
}