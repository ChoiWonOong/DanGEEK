package DanGEEK.app.controller;

import DanGEEK.app.domain.ChatRoom;
import DanGEEK.app.dto.chat.ChatRoomCreateDto;
import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.service.ChatRoomService;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;


    // 모든 채팅방 목록 반환
    @GetMapping("/list")
    @ResponseBody
    public List<ChatRoomResponseDto> findAllRooms() {
        return chatRoomService.findAllRooms();
    }
    @GetMapping("/my")
    @ResponseBody
    public List<ChatRoomResponseDto> findMyRooms() {
        return chatRoomService.findChatroomByUserId(SecurityUtil.getCurrentMemberId());
    }

    // 채팅방 생성
    @PostMapping("/create")
    @ResponseBody
    public ChatRoomResponseDto createRoom(@RequestBody ChatRoomCreateDto chatRoomCreateDto) {
        return chatRoomService.createChatRoom(chatRoomCreateDto.getName(), chatRoomCreateDto.getMaxUser()).toResponseDto();
    }

    // 특정 채팅방 정보 보기
    @GetMapping("/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable Long roomId) {
        return chatRoomService.findRoomById(roomId);
    }
}