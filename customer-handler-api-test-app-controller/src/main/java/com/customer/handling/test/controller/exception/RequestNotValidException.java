package com.customer.handling.test.controller.exception;

public class RequestNotValidException extends RuntimeException {

    public RequestNotValidException(String message) {
        super(message);
    }
}
