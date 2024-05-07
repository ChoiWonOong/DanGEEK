package DanGEEK.app.controller;

import DanGEEK.app.dto.NotificationSendDto;
import DanGEEK.app.service.NotificationService;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        return notificationService.subscribe(SecurityUtil.getCurrentMemberId());
    }

    @PostMapping("/send-data")
    public ResponseEntity<NotificationSendDto> sendData(@RequestBody NotificationSendDto notificationSendDto) {
        return ResponseEntity.ok(notificationService.notify(notificationSendDto));
    }
}