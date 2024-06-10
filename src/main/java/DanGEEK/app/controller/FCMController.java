package DanGEEK.app.controller;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.FCMRequestDto;
import DanGEEK.app.dto.token.FCMTokenDto;
import DanGEEK.app.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import DanGEEK.app.service.MemberService;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fcm")
public class FCMController {
    private final FCMService fcmService;
    private final MemberService memberService;

    @PostMapping("/send")
    public ResponseEntity<Integer> pushMessage(@RequestBody FCMRequestDto fcmRequestDto) throws IOException {
        return ResponseEntity.ok(fcmService.sendMessageTo(fcmRequestDto, false));
    }
    @PostMapping("/save")
    public ResponseEntity<FCMTokenDto> refreshAccessToken(@RequestBody FCMTokenDto dto) throws IOException {
        Member member = memberService.getMe();
        fcmService.saveToken(member, dto.getToken());
        return ResponseEntity.ok(dto);
    }
}
