package DanGEEK.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FlaskDto {
    String message;
    int result = 1; //default : pass
}