package work.mywork.scm.spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDl̥uplicateResource(DuplicateResourceException ex) {
        ApiError error = new ApiError(HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleBadRequest(MethodArgumentTypeMismatchException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Invalid parameter: " + ex.getName());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error occurred");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
