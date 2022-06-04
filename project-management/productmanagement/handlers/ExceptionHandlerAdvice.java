package com.example.productmanagement.handlers;

import com.example.productmanagement.exceptions.CustomerNotFoundException;
import com.example.productmanagement.exceptions.IllegalDeleteException;
import com.example.productmanagement.exceptions.ProductNotFoundException;
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
        return new ResponseEntity<>(JSONUtil.objectToJsonString(result), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalDeleteException.class)
    public ResponseEntity<Object> illegalDelete(IllegalDeleteException illegalDeleteException) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("Timestamp", LocalDateTime.now());
        result.put("Message", illegalDeleteException.getMessage());
        return new ResponseEntity<>(JSONUtil.objectToJsonString(result), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> productNotFound(ProductNotFoundException productNotFoundException) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("Timestamp", LocalDateTime.now());
        result.put("Message", productNotFoundException.getMessage());
        return new ResponseEntity<>(JSONUtil.objectToJsonString(result), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> customerNotFound(CustomerNotFoundException customerNotFoundException) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("Timestamp", LocalDateTime.now());
        result.put("Message", customerNotFoundException.getMessage());
        return new ResponseEntity<>(JSONUtil.objectToJsonString(result), HttpStatus.NOT_FOUND);
    }
}
