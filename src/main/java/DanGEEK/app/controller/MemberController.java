package DanGEEK.app.controller;

import DanGEEK.app.Exception.ErrorResponse;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberAnalyzeInfo;
import DanGEEK.app.dto.member.MemberCreateResponseDto;
import DanGEEK.app.dto.member.MemberIntroductionCreateDto;
import DanGEEK.app.dto.MyPageDto;
import DanGEEK.app.dto.member.SurveyRequestDto;
import DanGEEK.app.service.MemberService;
import DanGEEK.app.util.SecurityUtil;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

/*    @PostMapping("/signup")
    public ResponseEntity<?> writeAnalyzeInfo(@RequestBody MemberCreateResponseDto memberCreateResponseDto){
        try{
            return ResponseEntity.ok(memberService.writeAnalyzeInfo(memberCreateResponseDto));
        }
        catch (RestApiException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }*/
    @PostMapping("/write/introduction")
    public ResponseEntity<MemberIntroductionCreateDto> writeIntroduction(@RequestBody MemberIntroductionCreateDto memberIntroductionCreateDto){
        return ResponseEntity.ok(memberService.writeIntroduction(memberIntroductionCreateDto));
    }
    @PostMapping("/update/image")
    public ResponseEntity<MyPageDto> updateImage(@RequestPart(value = "image") MultipartFile image){
        return ResponseEntity.ok(memberService.updateImage(image));
    }
    @GetMapping("/mypage")
    public ResponseEntity<?> getMyPage(){
        try{
            return ResponseEntity.ok(memberService.getMyPage());
        }
        catch (RestApiException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
    @GetMapping("/introduction/{id}")
    public ResponseEntity<?> getMemberIntroduction(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(memberService.getMemberIntroduction(id));
        }
        catch (RestApiException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e);
        }
    }
    @GetMapping("/myintroduction")
    public ResponseEntity<?> getMyIntroduction(){
        try{
            return ResponseEntity.ok(memberService.getMyIntroduction());
        }
        catch (RestApiException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e);
        }
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
    public ResponseEntity<?> getRecommendedMember(){
        try{
            return ResponseEntity.ok(memberService.getRecommendedInstruction());
        }
        catch (RestApiException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e);
        }
    }
    @PostMapping("/write/survey")
    public ResponseEntity<?> writeSurvey(@RequestBody SurveyRequestDto surveyDto){
        try{
            return ResponseEntity.ok(memberService.writeSurvey(surveyDto));
        }catch (RuntimeException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e);
        }
    }
    @GetMapping("/get/analyzingInfo")
    public ResponseEntity<?> getAnalyzingInfo(){
        try{
            return ResponseEntity.ok(memberService.getAnalyzeInfo(SecurityUtil.getCurrentMemberId()));
        }
        catch (RestApiException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e);
        }
    }
}
