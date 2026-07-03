package lv.acnbootcamp.team6.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception e) {

        return buildResponse("CatchAll Internal Server Error or Bad Request", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, String>> buildResponse(String error, String message, HttpStatus status) {
        Map<String, String> body = new HashMap<>();
        body.put("error", error);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
