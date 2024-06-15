package DanGEEK.app.service;

import DanGEEK.app.domain.FCMToken;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.FCMRequestDto;
import DanGEEK.app.dto.FCMSendDto;
import DanGEEK.app.repository.FCMTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FCMService {
    private final FCMTokenRepository fcmRepository;
    /**
     * 푸시 메시지 처리를 수행하는 비즈니스 로직
     *
     * @param requestDto 모바일에서 전달받은 Object
     * @return 성공(1), 실패(0)
     */
    public int sendMessageTo(FCMRequestDto requestDto, boolean isValidate) throws IOException {

        String message = makeMessage(requestDto.getToken(), requestDto.getTitle(), requestDto.getBody(), isValidate);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity entity = new HttpEntity<>(message, headers);

        String API_URL = "https://fcm.googleapis.com/v1/projects/adjh54-a0189/messages:send";
        ResponseEntity response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        System.out.println(response.getStatusCode());

        return response.getStatusCode() == HttpStatus.OK ? 1 : 0;
    }
    /**
     * Firebase Admin SDK의 비공개 키를 참조하여 Bearer 토큰을 발급 받습니다.
     *
     * @return Bearer token
     */
    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "{FIREBASE_CONFIG_PATH}"; // Firebase Admin SDK의 비공개 키 경로

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("<https://www.googleapis.com/auth/cloud-platform>"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
    private String makeMessage(String token, String title, String body, boolean validate) throws IOException{

        ObjectMapper om = new ObjectMapper();
        FCMSendDto fcmSendDto = FCMSendDto.builder()
                .validate_only(validate)
                .message(FCMSendDto.Message.builder()
                        .token(token)
                        .title(title)
                        .body(body)
                        .build())
                .build();

        return om.writeValueAsString(fcmSendDto);
    }
    public void saveToken(Member member, String token){
        if(fcmRepository.existsByMember(member)){
            FCMToken fcmToken = fcmRepository.findByMember(member);
            fcmToken.updateToken(token);
        }
        FCMToken fcmToken = new FCMToken(member, token);
        fcmRepository.save(fcmToken);
    }
}
