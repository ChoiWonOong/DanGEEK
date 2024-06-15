package DanGEEK.app.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RestApiException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(HttpServletRequest request, RestApiException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
