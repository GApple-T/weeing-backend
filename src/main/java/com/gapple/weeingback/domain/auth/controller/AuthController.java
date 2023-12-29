package com.gapple.weeingback.domain.auth.controller;

import com.gapple.weeingback.domain.auth.domain.dto.request.AuthJoinRequest;
import com.gapple.weeingback.domain.auth.domain.dto.request.AuthLoginRequest;
import com.gapple.weeingback.domain.auth.domain.dto.request.EmailCertifyRequest;
import com.gapple.weeingback.domain.auth.domain.dto.response.AuthLoginResponse;
import com.gapple.weeingback.domain.auth.domain.dto.response.AuthLogoutResponse;
import com.gapple.weeingback.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@Valid @RequestBody AuthJoinRequest request){
        authService.join(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest request){
        AuthLoginResponse response = authService.login(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @Valid @RequestHeader(name = "Authorization") String authorization,
            @Valid @RequestHeader(name = "refresh-token") String refresh){
        authService.logout(authorization, refresh);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthLogoutResponse> refresh(
            @Valid @RequestHeader(name = "Authorization") String authorization,
            @Valid @RequestHeader(name = "refresh-token") String refresh){
        return ResponseEntity.ok().body(authService.refresh(authorization, refresh));
    }

    @PostMapping("/send-auth")
    public ResponseEntity<?> emailCertify(@Valid @RequestBody EmailCertifyRequest request){
        return authService.sendAuth(request);
    }
}
