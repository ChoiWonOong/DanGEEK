package DanGEEK.app.controller;

import DanGEEK.app.Exception.ErrorResponse;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.dto.chat.ChatRoomCreateDto;
import DanGEEK.app.dto.chat.ChatRoomResponseDto;
import DanGEEK.app.service.ChatRoomService;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;


    // 모든 채팅방 목록 반환
    @GetMapping("/list")
    public List<ChatRoomResponseDto> findAllRooms() {
        return chatRoomService.findAllRooms();
    }
    @GetMapping("/my")
    public ResponseEntity<?> findMyRooms() {
        try {
            return ResponseEntity.ok(chatRoomService.findChatroomByUserId(SecurityUtil.getCurrentMemberId()));
        } catch (RestApiException e) {
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }

    // 채팅방 생성
    @PostMapping("/create")
    public ChatRoomResponseDto createRoom(@RequestBody ChatRoomCreateDto chatRoomCreateDto) {
        return chatRoomService.createChatRoom(chatRoomCreateDto.getName(), chatRoomCreateDto.getMaxUser()).toResponseDto();
    }

    // 특정 채팅방 정보 보기
    @GetMapping("/{roomId}")
    public ResponseEntity<?> roomInfo(@PathVariable(value = "roomId") Long roomId) {
        try{
            return ResponseEntity.ok(chatRoomService.findRoomById(roomId));
        } catch (RestApiException e) {
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
}