package DanGEEK.app.controller;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.ErrorResponse;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.dto.UnivCertification.UnivCertifyCodeRequestDto;
import DanGEEK.app.dto.UnivCertification.UnivCertifyRequestDto;
import DanGEEK.app.dto.auth.EmailCheckDto;
import DanGEEK.app.dto.auth.NicknameCheckDto;
import DanGEEK.app.dto.member.MemberCreateRequestDto;
import DanGEEK.app.dto.member.MemberCreateResponseDto;
import DanGEEK.app.dto.member.MemberPasswordReassignDto;
import DanGEEK.app.dto.token.TokenDto;
import DanGEEK.app.dto.token.TokenRequestDto;
import DanGEEK.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/univ_certify")
    public ResponseEntity<Map<String, Object>> univCertify(@RequestBody UnivCertifyRequestDto univCertifyRequestDto) throws IOException {
        return ResponseEntity.ok(authService.univCertify(univCertifyRequestDto));
    }
    @PostMapping("/check/email")
    public ResponseEntity<EmailCheckDto> checkEmail(@RequestBody EmailCheckDto dto) {
        return ResponseEntity.ok(authService.checkEmailRedundant(dto));
    }
    @PostMapping("/check/nickname")
    public ResponseEntity<NicknameCheckDto> checkNickname(@RequestBody NicknameCheckDto dto) {
        return ResponseEntity.ok(authService.checkNicknameRedundant(dto));
    }
    @PostMapping("/univ_certify_code")
    public ResponseEntity<Map<String, Object>> univCertifyCode(@RequestBody UnivCertifyCodeRequestDto univCertifyCodeRequestDto) throws IOException {
        return ResponseEntity.ok(authService.univCertifyCode(univCertifyCodeRequestDto));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> memberSignup(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        try{
            return ResponseEntity.ok(authService.memberSignup(memberCreateRequestDto));
        } catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> memberLogin(@RequestBody MemberCreateRequestDto memberCreateRequestDto){
        try{
            return ResponseEntity.ok(authService.memberLogin(memberCreateRequestDto));
        } catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
    @PatchMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal User user, @RequestBody TokenDto tokenDto) {
        return ResponseEntity.ok(authService.logout(tokenDto.getAccessToken(), user));
    }
    @PostMapping("/reassign/certify")
    public ResponseEntity<Map<String,Object>> passwordReassignCertify(@RequestBody UnivCertifyRequestDto univCertifyRequestDto) throws IOException{
        return ResponseEntity.ok(authService.passwordReassignCertify(univCertifyRequestDto));
    }
    @PostMapping("/reassign/certify_code")
    public ResponseEntity<Map<String,Object>> passwordReassignCertifyCode(@RequestBody UnivCertifyCodeRequestDto univCertifyCodeRequestDto) throws IOException{
        return ResponseEntity.ok(authService.passwordReassignCertifyCode(univCertifyCodeRequestDto));
    }
    @PatchMapping("/reassign/password")
    public ResponseEntity<MemberPasswordReassignDto> passwordReassign(@RequestBody MemberPasswordReassignDto memberPasswordReassignDto){
        return ResponseEntity.ok(authService.passwordReassign(memberPasswordReassignDto));
    }
}
