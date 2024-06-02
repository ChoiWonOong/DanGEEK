package DanGEEK.app.controller;

import DanGEEK.app.dto.FCMRequestDto;
import DanGEEK.app.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fcm")
public class FCMController {
    private final FCMService fcmService;
    @PostMapping("/send")
    public ResponseEntity<Integer> pushMessage(@RequestBody FCMRequestDto fcmRequestDto) throws IOException {
        return ResponseEntity.ok(fcmService.sendMessageTo(fcmRequestDto, false));
    }
}
