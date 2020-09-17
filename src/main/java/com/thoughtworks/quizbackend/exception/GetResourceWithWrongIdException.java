package com.thoughtworks.quizbackend.exception;

// GTB: - 异常的名字不太好，改进一下
public class GetResourceWithWrongIdException extends RuntimeException {
    public GetResourceWithWrongIdException(String message) {super(message);}
}
