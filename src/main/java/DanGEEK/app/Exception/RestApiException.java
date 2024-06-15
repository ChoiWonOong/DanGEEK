package DanGEEK.app.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
public class RestApiException extends RuntimeException{
    private ErrorCode errorCode;
    public RestApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
