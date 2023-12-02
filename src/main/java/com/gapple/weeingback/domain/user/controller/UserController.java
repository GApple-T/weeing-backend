package com.gapple.weeingback.domain.user.controller;

import com.gapple.weeingback.global.email.service.EmailService;
import com.gapple.weeingback.global.jwt.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gapple.weeingback.domain.user.entity.dto.UserJoinRequest;
import com.gapple.weeingback.domain.user.entity.dto.UserLoginRequest;
import com.gapple.weeingback.domain.user.service.implmentation.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserServiceImpl service;
  private final EmailService emailService;

  @PostMapping("/join")
  public ResponseEntity<?> join(@Valid @RequestBody UserJoinRequest request){
    return service.join(request);
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody UserLoginRequest request){
    return service.login(request);
  }

  @PostMapping("/find")
  public void findMyAccount(){
    emailService.sendMail("me@xolving.com", "안보내지면 귀찮은데?", "안보내지면 귀찮은 과정이 있을 예정임");
  }
}
