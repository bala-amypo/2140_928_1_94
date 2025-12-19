package com.example.demo.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message); // message must contain "capacity" or other bad request info
    }
}
