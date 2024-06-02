package DanGEEK.app.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class FCMSendDto {
    private boolean validate_only;
    private Message message;
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private String title;
        private String body;
        private String token;
    }

    @Builder(toBuilder = true)
    public FCMSendDto(boolean validate_only, Message message) {
        this.validate_only = validate_only;
        this.message = Message.builder()
                .title(message.title)
                .body(message.body)
                .build();
    }
}