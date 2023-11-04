package com.gapple.weeingback.domain.user.controller;

import com.gapple.weeingback.global.jwt.TokenResponse;
import jakarta.validation.Valid;
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
  public ResponseEntity<Void> join(@Valid @RequestBody UserJoinRequest request) throws Exception {
    service.join(request);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest request) throws IllegalAccessException {
    return new ResponseEntity<>(service.login(request), HttpStatus.ACCEPTED);
  }
}
