package com.example.productmanagement.exceptions;

public class IllegalDeleteException extends RuntimeException { //derivate din RunTime
    public IllegalDeleteException(String message) {
        super(message);
    }
}
