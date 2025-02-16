/*
package com.example.careit.controller;

import com.example.careit.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        // BadRequestException 발생 시 400 상태 코드와 메시지 반환
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
*/