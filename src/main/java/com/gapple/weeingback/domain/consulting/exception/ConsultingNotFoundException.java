package com.gapple.weeingback.domain.consulting.exception;

public class ConsultingNotFoundException extends RuntimeException{
    public ConsultingNotFoundException(String error){
        super(error);
    }
}
