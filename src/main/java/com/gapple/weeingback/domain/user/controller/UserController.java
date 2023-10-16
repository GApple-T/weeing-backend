package com.gapple.weeingback.domain.user.controller;

import com.gapple.weeingback.domain.user.entity.User;
import com.gapple.weeingback.domain.user.entity.dto.UserJoinRequest;
import com.gapple.weeingback.domain.user.entity.dto.UserLoginRequest;
import com.gapple.weeingback.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/join")
  public void join(@RequestBody UserJoinRequest request) throws Exception {
    userService.join(request);
  }

  @PostMapping("/login")
  public void login(@RequestBody UserLoginRequest request){
    userService.login(request);
  }

  @PostMapping("/token")
  public String giveToken(@RequestBody UserLoginRequest request){
    userService.token(request);
    return null;
  }

  @GetMapping("/")
  public String hello(@AuthenticationPrincipal Object principal){
    return principal.toString();
  }
}
