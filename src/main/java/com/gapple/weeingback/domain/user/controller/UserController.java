package com.gapple.weeingback.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gapple.weeingback.domain.user.entity.dto.UserJoinRequest;
import com.gapple.weeingback.domain.user.entity.dto.UserLoginRequest;
import com.gapple.weeingback.domain.user.service.implmentation.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserServiceImpl service;

  @PostMapping("/join")
  public ResponseEntity<Void> join(@RequestBody UserJoinRequest request) throws Exception {
    service.join(request);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserLoginRequest request){
    service.login(request);
    return new ResponseEntity<>(null, HttpStatus.ACCEPTED); // TODO JWT 토큰 발급 로직이 만들어지고 변경될 예정
  }
}
