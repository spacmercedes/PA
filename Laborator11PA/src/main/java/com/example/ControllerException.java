package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(value = {ResourceNotFound.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFound exeption, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                404,
                new Date(System.currentTimeMillis()),
                exeption.getMessage(),
                "Testing this request");

        return message;
    }
}
