package ma.norsys.bookstore.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiError> handelBookNotFound(BookNotFoundException exception, WebRequest request) {

        ApiError errorObject = new ApiError();
        errorObject.setStatue(HttpStatus.NOT_FOUND);
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ApiError>(errorObject, HttpStatus.NOT_FOUND);
    }
}
