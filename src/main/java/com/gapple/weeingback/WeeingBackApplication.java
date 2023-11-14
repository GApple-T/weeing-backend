package com.gapple.weeingback;

import com.gapple.weeingback.global.email.service.EmailService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;

@SpringBootApplication
public class WeeingBackApplication {
  public static void main(String[] args) {
    SpringApplication.run(WeeingBackApplication.class, args);
  }
}