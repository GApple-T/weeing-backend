package com.gapple.weeingback.domain.user.controller;

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
  private final UserServiceImpl userService;

  @PostMapping("/join")
  public void join(@RequestBody UserJoinRequest request) throws Exception {
    userService.join(request);
  }

  @PostMapping("/login")
  public void login(@RequestBody UserLoginRequest request){
    userService.login(request);
  }
}
