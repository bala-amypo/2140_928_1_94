package com.example.demo.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message); // message should contain "capacity" or invalid input info
    }
}
