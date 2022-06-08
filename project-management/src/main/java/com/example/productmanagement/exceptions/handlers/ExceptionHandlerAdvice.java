package com.example.productmanagement.exceptions.handlers;

import com.example.productmanagement.exceptions.IllegalDeleteException;
import com.example.productmanagement.exceptions.UserAlreadyExistsException;
import com.example.productmanagement.util.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> userAlreadyExists(UserAlreadyExistsException userAlreadyExistsException) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("Timestamp", LocalDateTime.now());
        result.put("Message", userAlreadyExistsException.getMessage());
        return new ResponseEntity<>(JSONUtil.objectToJsonString(result), HttpStatus.NOT_ACCEPTABLE); //daca primeste exceptia UserAlreadyExists
    } //creeaza un JSON si trimite statusul de NOT_ACCEPTABLE, facand handle la exceptie mai elegant fara parsa o exceptie de la o metoda la alta

    @ExceptionHandler(IllegalDeleteException.class)
    public ResponseEntity<Object> illegalDelete(IllegalDeleteException illegalDeleteException) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("Timestamp", LocalDateTime.now());
        result.put("Message", illegalDeleteException.getMessage());
        return new ResponseEntity<>(JSONUtil.objectToJsonString(result), HttpStatus.NOT_ACCEPTABLE);
    }
}
