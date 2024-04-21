package DanGEEK.app.controller;

import DanGEEK.app.dto.MemberCreateRequestDto;
import DanGEEK.app.dto.MemberCreateResponseDto;
import DanGEEK.app.dto.UnivCertification.UnivCertifyCodeRequestDto;
import DanGEEK.app.dto.UnivCertification.UnivCertifyRequestDto;
import DanGEEK.app.dto.token.TokenDto;
import DanGEEK.app.dto.token.TokenRequestDto;
import DanGEEK.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/univ_certify")
    public ResponseEntity<Map<String, Object>> univCertify(@RequestBody UnivCertifyRequestDto univCertifyRequestDto) throws IOException {
        return ResponseEntity.ok(authService.univCertify(univCertifyRequestDto));
    }
    @PostMapping("/univ_certify_code")
    public ResponseEntity<Map<String, Object>> univCertifyCode(@RequestBody UnivCertifyCodeRequestDto univCertifyCodeRequestDto) throws IOException {
        return ResponseEntity.ok(authService.univCertifyCode(univCertifyCodeRequestDto));
    }
    @PostMapping("/signup")
    public ResponseEntity<MemberCreateResponseDto> memberSignup(@RequestBody MemberCreateRequestDto memberCreateRequestDto){
        return ResponseEntity.ok(authService.memberSignup(memberCreateRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> memberLogin(@RequestBody MemberCreateRequestDto memberCreateRequestDto){
        return ResponseEntity.ok(authService.memberLogin(memberCreateRequestDto));
    }
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
    @PatchMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal User user, @RequestBody TokenDto tokenDto) {
        return ResponseEntity.ok(authService.logout(tokenDto.getAccessToken(), user));
    }
}
