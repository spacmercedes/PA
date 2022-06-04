package com.example.productmanagement.exceptions;

public class IllegalDeleteException extends RuntimeException {
    public IllegalDeleteException(String message) {
        super(message);
    }
}
