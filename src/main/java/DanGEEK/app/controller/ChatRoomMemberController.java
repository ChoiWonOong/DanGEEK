package DanGEEK.app.controller;

import DanGEEK.app.Exception.ErrorResponse;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import DanGEEK.app.service.ChatRoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomMemberController {
    private final ChatRoomMemberService chatRoomMemberService;
    @PostMapping("/enter/{roomId}")
    public ChatRoomMemberCreateResponseDto createChatRoom(@PathVariable Long roomId, Long senderId) {
        // 채팅방 입장
        return chatRoomMemberService.createChatRoomMember(roomId, senderId).toResponseDto();
    }
    @PostMapping("/exit/{roomId}")
    public ResponseEntity<?> exitChatRoom(@PathVariable Long roomId, Long senderId) {
        // 채팅방 퇴장
        try{
            chatRoomMemberService.deleteChatRoomMember(roomId, senderId);
            return ResponseEntity.ok().build();
        } catch (RestApiException e) {
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
    @GetMapping("/members/{roomId}")
    public ResponseEntity<?> getChatRoomMembers(@PathVariable Long roomId) {
        // 채팅방 참여자 목록 조회
        try{
            return ResponseEntity.ok(chatRoomMemberService.findAllMemberNameByRoomId(roomId));
        } catch (RestApiException e) {
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
}
