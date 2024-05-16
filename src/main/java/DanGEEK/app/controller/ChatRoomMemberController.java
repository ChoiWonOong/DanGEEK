package DanGEEK.app.controller;

import DanGEEK.app.dto.chat.ChatRoomMemberCreateResponseDto;
import DanGEEK.app.service.ChatRoomMemberService;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomMemberController {
    private final ChatRoomMemberService chatRoomMemberService;
    @PostMapping("/enter/{roomId}")
    public ChatRoomMemberCreateResponseDto enterChatRoom(@PathVariable Long roomId) {
        // 채팅방 입장
        return chatRoomMemberService.createChatRoomMember(roomId, SecurityUtil.getCurrentMemberId());
    }
    public void exitChatRoom(Long roomId) {
        // 채팅방 퇴장
        chatRoomMemberService.deleteChatRoomMember(roomId, SecurityUtil.getCurrentMemberId());
    }
    @GetMapping("/members")
    public List<String> getChatRoomMembers(Long roomId) {
        // 채팅방 참여자 목록 조회
        return chatRoomMemberService.findAllMemberNameByRoomId(roomId);
    }
}
