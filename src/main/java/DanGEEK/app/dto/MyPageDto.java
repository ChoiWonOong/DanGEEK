package DanGEEK.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageDto {
    String username;
    String nickname;
    Boolean introductionWritten;
    Boolean surveyDone;
    Boolean putOnRecommend;
    String imageUrl;
}
