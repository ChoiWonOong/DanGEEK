package DanGEEK.app.controller;

import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import DanGEEK.app.dto.MyPageDto;
import DanGEEK.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<MemberIntroductionCreateDto> getMemberIntroduction(@PathVariable("id") Long id){
        return ResponseEntity.ok(memberService.getMemberIntroduction(id));
    }
    @GetMapping("/myintroduction")
    public ResponseEntity<MemberIntroductionCreateDto> getMyIntroduction(){
        return ResponseEntity.ok(memberService.getMyIntroduction());
    }
    @GetMapping("/release")
    public ResponseEntity<MyPageDto> releaseMember(){
        return ResponseEntity.ok(memberService.releaseMember());
    }
    @GetMapping("/hold")
    public ResponseEntity<MyPageDto> holdMember(){
        return ResponseEntity.ok(memberService.holdMember());
    }
    @GetMapping("recommend")
    public ResponseEntity<List<MemberIntroductionCreateDto>> getRecommendedMember(){
        return ResponseEntity.ok(memberService.getRecommendedInstruction());
    }
}
