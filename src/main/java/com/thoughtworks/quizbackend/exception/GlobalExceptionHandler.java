package com.thoughtworks.quizbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handler(MethodArgumentNotValidException exception) {
        String message = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();
        ErrorResult errorResult = ErrorResult.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(message)
                .build();
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(GetResourceWithWrongIdException.class)
    public ResponseEntity handler(GetResourceWithWrongIdException exception) {
        String message = exception.getMessage();
        // GTB: - 为什么这里不使用 ErrorResult？
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
