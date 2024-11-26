package DanGEEK.app.controller;

import DanGEEK.app.Exception.ErrorResponse;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.chat.ChatRequestDto;
import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import DanGEEK.app.service.ChatRoomMemberService;
import DanGEEK.app.service.ChatService;
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
    public ResponseEntity<?> createChatRoom(@PathVariable(value = "roomId") Long roomId) {
        try {
            // 채팅방 입장
            return ResponseEntity.ok(chatRoomMemberService.createChatRoomMember(roomId).toResponseDto());
        }catch (RuntimeException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e);
        }
    }
    @PostMapping("/exit/{roomId}")
    public ResponseEntity<?> exitChatRoom(@PathVariable(value = "roomId") Long roomId) {
        // 채팅방 퇴장
        try{
            return ResponseEntity.ok(chatRoomMemberService.deleteChatRoomMember(roomId));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e);
        }
    }
    @GetMapping("/members/{roomId}")
    public ResponseEntity<?> getChatRoomMembers(@PathVariable(value = "roomId") Long roomId) {
        // 채팅방 참여자 목록 조회
        try{
            return ResponseEntity.ok(chatRoomMemberService.findAllMemberNameByRoomId(roomId));
        } catch (RestApiException e) {
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
}
