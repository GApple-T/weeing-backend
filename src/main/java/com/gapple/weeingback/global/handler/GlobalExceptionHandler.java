package com.gapple.weeingback.global.handler;

import com.gapple.weeingback.global.exception.MemberExistsException;
import com.gapple.weeingback.global.exception.MemberNotFoundException;
import com.gapple.weeingback.global.exception.PasswordNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<HttpStatus> handleMethodPasswordNotMatchException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<HttpStatus> handleMethodMemberNotFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberExistsException.class)
    public ResponseEntity<HttpStatus> handleMethodMemberExistsException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}