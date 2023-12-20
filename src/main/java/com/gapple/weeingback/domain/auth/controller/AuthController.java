package com.gapple.weeingback.domain.auth.controller;

import com.gapple.weeingback.domain.auth.domain.dto.*;
import com.gapple.weeingback.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<AuthJoinResponse> join(@Valid @RequestBody AuthJoinRequest request){
        return authService.join(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthLoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @Valid @RequestHeader(name = "Authorization", required = true) String authorization,
            @Valid @RequestHeader(name = "refresh-token", required = true) String refresh){
        return authService.logout(authorization, refresh);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthLogoutResponse> refresh(
            @Valid @RequestHeader(name = "Authorization", required = true) String authorization,
            @Valid @RequestHeader(name = "refresh-token", required = true) String refresh){
        log.info("headerAccess={}, headerRefresh={}", authorization, refresh);
        return authService.refresh(authorization, refresh);
    }

    @PostMapping("/send-auth")
    public ResponseEntity<?> emailCertify(@Valid @RequestBody EmailCertifyRequest request){
        return authService.sendAuth(request);
    }
}
