package DanGEEK.app.controller;

import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import DanGEEK.app.service.ChatRoomMemberService;
import lombok.RequiredArgsConstructor;
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
        return chatRoomMemberService.enterChatRoomMember(roomId, senderId);
    }
    @PostMapping("/exit/{roomId}")
    public void exitChatRoom(@PathVariable Long roomId, Long senderId) {
        // 채팅방 퇴장
        chatRoomMemberService.deleteChatRoomMember(roomId, senderId);
    }
    @GetMapping("/members/{roomId}")
    public List<String> getChatRoomMembers(@PathVariable Long roomId) {
        // 채팅방 참여자 목록 조회
        return chatRoomMemberService.findAllMemberNameByRoomId(roomId);
    }
}
