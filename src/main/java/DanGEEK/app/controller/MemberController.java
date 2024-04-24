package DanGEEK.app.controller;

import DanGEEK.app.dto.MemberIntroductionCreateDto;
import DanGEEK.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/introduction/write")
    public ResponseEntity<MemberIntroductionCreateDto> writeIntroduction(@RequestBody MemberIntroductionCreateDto memberIntroductionCreateDto){
        return ResponseEntity.ok(memberService.writeIntroduction(memberIntroductionCreateDto));

    }
}
