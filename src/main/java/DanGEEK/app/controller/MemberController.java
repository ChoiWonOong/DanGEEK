package DanGEEK.app.controller;

import DanGEEK.app.dto.Member.MemberIntroductionCreateDto;
import DanGEEK.app.dto.MyPageDto;
import DanGEEK.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/write/introduction")
    public ResponseEntity<MemberIntroductionCreateDto> writeIntroduction(@RequestBody MemberIntroductionCreateDto memberIntroductionCreateDto){
        return ResponseEntity.ok(memberService.writeIntroduction(memberIntroductionCreateDto));
    }
    @GetMapping("/mypage")
    public ResponseEntity<MyPageDto> getMyPage(){
        return ResponseEntity.ok(memberService.getMyPage());
    }
    @GetMapping("/introduction/{id}")
    public ResponseEntity<MemberIntroductionCreateDto> getMemberIntroduction(@PathVariable Long id){
        return ResponseEntity.ok(memberService.getMemberIntroduction(id));
    }
}
