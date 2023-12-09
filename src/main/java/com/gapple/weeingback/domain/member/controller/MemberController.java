package com.gapple.weeingback.domain.member.controller;

import com.gapple.weeingback.global.email.service.EmailService;
import com.gapple.weeingback.global.jwt.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gapple.weeingback.domain.member.entity.dto.MemberJoinRequest;
import com.gapple.weeingback.domain.member.entity.dto.MemberLoginRequest;
import com.gapple.weeingback.domain.member.service.implmentation.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  private final MemberServiceImpl memberService;
  private final EmailService emailService;

  @PostMapping("/join")
  public ResponseEntity<?> join(@Valid @RequestBody MemberJoinRequest request){
    return memberService.join(request);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody MemberLoginRequest request){
    return memberService.login(request);
  }

  @PostMapping("/find")
  public void findMyAccount(){
    emailService.sendMail("me@xolving.com", "안보내지면 귀찮은데?", "안보내지면 귀찮은 과정이 있을 예정임");
  }
}
