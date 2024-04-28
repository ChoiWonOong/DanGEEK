package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Member;
import DanGEEK.app.dto.MemberCreateRequestDto;
import DanGEEK.app.dto.MemberCreateResponseDto;
import DanGEEK.app.dto.MemberPasswordReassignDto;
import DanGEEK.app.dto.UnivCertification.UnivCertifyCodeRequestDto;
import DanGEEK.app.dto.UnivCertification.UnivCertifyRequestDto;
import DanGEEK.app.dto.token.TokenDto;
import DanGEEK.app.dto.token.TokenRequestDto;
import DanGEEK.app.jwt.RefreshToken;
import DanGEEK.app.jwt.TokenProvider;
import DanGEEK.app.repository.MemberRepository;
import DanGEEK.app.repository.RefreshTokenRepository;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    @Value("${univ.key}")
    String key;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public Map<String, Object> univCertify(UnivCertifyRequestDto univCertifyRequestDto) throws IOException {
        if (memberRepository.existsByUsername(univCertifyRequestDto.getEmail())) {
            throw new RestApiException(ErrorCode.ALREADY_EXIST_ERROR);
        }
        return UnivCert.certify(key, univCertifyRequestDto.getEmail(), "단국대학교", true);
    }
    public Map<String, Object> univCertifyCode(UnivCertifyCodeRequestDto univCertifyCodeRequestDto) throws IOException {
        return UnivCert.certifyCode(key, univCertifyCodeRequestDto.getEmail(), "단국대학교", univCertifyCodeRequestDto.getCode());
    }
    public MemberCreateResponseDto memberSignup(MemberCreateRequestDto memberCreateRequestDto) {
        if (memberRepository.existByNickname(memberCreateRequestDto.getNickname())) {
            throw new RestApiException(ErrorCode.ALREADY_EXIST_ERROR);
        }
        Member member = memberCreateRequestDto.toMember(passwordEncoder);
        return Member.memberToResponseDto(memberRepository.save(member));
    }
    public Map<String, Object> passwordReassignCertify(UnivCertifyRequestDto univCertifyRequestDto) throws IOException{
        if(memberRepository.findByUsername(univCertifyRequestDto.getEmail()).isPresent()){
            return UnivCert.certify(key, univCertifyRequestDto.getEmail(), "단국대학교", false);
        }
        return null;
    }
    public Map<String, Object> passwordReassignCertifyCode(UnivCertifyCodeRequestDto univCertifyCodeRequestDto) throws IOException {
        return UnivCert.certifyCode(key, univCertifyCodeRequestDto.getEmail(), "단국대학교", univCertifyCodeRequestDto.getCode());
    }
    @Transactional
    public TokenDto memberLogin(MemberCreateRequestDto memberCreateRequestDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberCreateRequestDto.toAuthentication();
        System.out.println(authenticationToken.toString());
        TokenDto tokenDto = getToken(authenticationToken);
        System.out.println(tokenDto.getAccessToken());
        return tokenDto;
    }
    @Transactional
    public String logout(String accessToken, User user){
        String username = user.getUsername();
        refreshTokenRepository.deleteRefreshTokenByKey(username);

        return "logged out";
    }
    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));
        System.out.println("id : " + authentication.getName());

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
    public TokenDto getToken(UsernamePasswordAuthenticationToken authenticationToken){
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        System.out.println(tokenDto);
        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    public MemberPasswordReassignDto passwordReassign(MemberPasswordReassignDto memberPasswordReassignDto){
        Optional<Member> optionalMember = memberRepository.findByUsername(memberPasswordReassignDto.getUsername());
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            member.passwordReassign(passwordEncoder.encode(memberPasswordReassignDto.getPassword()));
            memberRepository.save(member);
            return new MemberPasswordReassignDto(memberPasswordReassignDto.getUsername(), true);
        }
        return new MemberPasswordReassignDto(memberPasswordReassignDto.getUsername(), false);
    }
}
