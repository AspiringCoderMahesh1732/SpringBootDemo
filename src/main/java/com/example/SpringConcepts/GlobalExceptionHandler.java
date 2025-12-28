package com.example.SpringConcepts;

import com.example.SpringConcepts.exception.UnknownCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(UnknownCredentials.class)
//    public ResponseEntity<String> throwUnknownCredentials(UnknownCredentials e){
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> throwException(Exception e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
