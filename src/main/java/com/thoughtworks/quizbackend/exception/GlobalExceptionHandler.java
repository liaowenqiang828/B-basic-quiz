package com.thoughtworks.quizbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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

    @ExceptionHandler(IdNotMatchedException.class)
    public ResponseEntity<ErrorResult> handler(IdNotMatchedException exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = ErrorResult.builder()
                .message(message)
                .error("Not Found")
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.of(Optional.of(errorResult));
    }
}
