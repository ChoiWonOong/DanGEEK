package DanGEEK.app.Exception;

import lombok.Getter;
@Getter
public class RestApiException extends RuntimeException{
    private ErrorCode errorCode;
    private StackTraceElement stackTraceElement;
    public RestApiException(ErrorCode errorCode, StackTraceElement stackTraceElement){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.stackTraceElement = stackTraceElement;
    }
    public RestApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
