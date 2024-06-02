package DanGEEK.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FCMRequestDto {
    private String token;
    private String title;
    private String body;
}
