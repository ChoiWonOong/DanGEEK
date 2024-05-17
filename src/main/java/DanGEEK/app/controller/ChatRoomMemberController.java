package DanGEEK.app.controller;

import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import DanGEEK.app.service.ChatRoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomMemberController {
    private final ChatRoomMemberService chatRoomMemberService;
    @PostMapping("/enter/{roomId}")
    public ChatRoomMemberCreateResponseDto createChatRoom(@PathVariable Long roomId, String sender) {
        // 채팅방 입장
        return chatRoomMemberService.enterChatRoomMember(roomId, sender);
    }
    @PostMapping("/exit/{roomId}")
    public void exitChatRoom(@PathVariable Long roomId, String sender) {
        // 채팅방 퇴장
        chatRoomMemberService.deleteChatRoomMember(roomId, sender);
    }
    @GetMapping("/members")
    public List<String> getChatRoomMembers(Long roomId) {
        // 채팅방 참여자 목록 조회
        return chatRoomMemberService.findAllMemberNameByRoomId(roomId);
    }
}
