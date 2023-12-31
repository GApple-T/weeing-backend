package com.gapple.weeingback.global.handler;

import com.gapple.weeingback.global.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(BoardgameExistsException.class)
    public ResponseEntity<HttpStatus> handleMethodBoardgameExistsException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SameCreatorException.class)
    public ResponseEntity<HttpStatus> handleMethodSameCreatorException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SameConsultingException.class)
    public ResponseEntity<HttpStatus> handleMethodSameConsultingException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<HttpStatus> handleMethodTokenNotFoundException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotEqualsException.class)
    public ResponseEntity<HttpStatus> handleMethodTokenNotEqualsException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenValidateException.class)
    public ResponseEntity<HttpStatus> handleMethodTokenValidateException(){
        return new ResponseEntity<>(HttpStatus.SEE_OTHER);
    }

    @ExceptionHandler(MailSendingException.class)
    public ResponseEntity<HttpStatus> handleMethodMailSendingException(){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpStatus> handleMethodArgumentNotValidException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConsultingNotFoundException.class)
    public ResponseEntity<HttpStatus> consultingNotFoundException(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BoardgameNotFoundException.class)
    public ResponseEntity<HttpStatus> baordgameNotFoundException(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}