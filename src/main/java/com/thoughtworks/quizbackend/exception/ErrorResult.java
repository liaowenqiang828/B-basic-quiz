package com.thoughtworks.quizbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult extends RuntimeException {
    private LocalDateTime timestamp;
    private String message;
    private int code;
    private String error;
}
