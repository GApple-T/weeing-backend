package com.gapple.weeingback.domain.auth.controller;

import com.gapple.weeingback.domain.auth.dto.*;
import com.gapple.weeingback.domain.auth.service.impl.AuthServiceImpl;
import com.gapple.weeingback.global.email.service.impl.EmailServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;
    private final EmailServiceImpl emailService;

    @PostMapping("/join")
    public ResponseEntity<AuthJoinResponse> join(@Valid @RequestBody AuthJoinRequest request){
        return authService.join(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest request){
        return authService.login(request);
    }
    @PostMapping("/mailauth")
    public ResponseEntity<String> emailCertify(@Valid @RequestBody EmailCertifyRequest request){
        return ResponseEntity.ok(emailService.sendMail(request.getEmail()));
    }
}
