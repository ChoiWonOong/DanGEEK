package DanGEEK.app.controller;

import DanGEEK.app.domain.ChatRoom;
import DanGEEK.app.dto.chat.ChatRoomCreateDto;
import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;


    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomCreateDto> findAllRooms() {
        return chatRoomService.findAllRooms();
    }


    // 채팅방 생성
    @PostMapping("/create")
    @ResponseBody
    public ChatRoomResponseDto createRoom(@RequestBody ChatRoomCreateDto chatRoomCreateDto) {
        return chatRoomService.createChatRoom(chatRoomCreateDto);
    }


    // 특정 채팅방 조회
    @GetMapping("/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable Long roomId) {
        return chatRoomService.findRoomById(roomId);
    }
}