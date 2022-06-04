package com.example.productmanagement.exceptions;

public class UserAlreadyExistsException extends RuntimeException { //derivate din RunTime
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
