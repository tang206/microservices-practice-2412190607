package com.zjgsu.tfy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 通用异常捕获
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 兜底捕获所有异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception e) {
        return new ResponseEntity<>("服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}