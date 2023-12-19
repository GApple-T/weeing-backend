package com.gapple.weeingback.global.email.service;

public interface EmailService {
    String sendAuth(String to);
    String createCode();
}
