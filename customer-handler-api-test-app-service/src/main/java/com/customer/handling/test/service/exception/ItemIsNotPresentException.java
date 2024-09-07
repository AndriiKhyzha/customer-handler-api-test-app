package com.customer.handling.test.service.exception;

public class ItemIsNotPresentException extends RuntimeException {
    public ItemIsNotPresentException(String message) {
        super(message);
    }
}
