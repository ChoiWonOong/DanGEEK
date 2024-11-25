package DanGEEK.app.Exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@Data
@Getter
@Builder
@ToString
public class  ErrorResponse {
    private int status;
    private String error;
    private String message;
    private StackTraceElement stackTrace;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(e.getHttpStatus().value())
                        .error(e.name())
                        .message(e.getMessage())
                        .build()
                );
    }
    public static ResponseEntity<ErrorResponse> toResponseEntity(RuntimeException e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .stackTrace(e.getStackTrace()[0])
                        .build()
                );
    }
}
