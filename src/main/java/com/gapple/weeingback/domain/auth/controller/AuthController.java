package com.gapple.weeingback.domain.auth.controller;

import com.gapple.weeingback.domain.auth.dto.*;
import com.gapple.weeingback.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestHeader(name = "Authorization", required = true) String authorization,
                                    @Valid @RequestHeader(name = "refresh-token", required = true) String refresh){
        return authService.logout(authorization, refresh);
    }

    @PostMapping("/send-auth")
    public ResponseEntity<String> emailCertify(@Valid @RequestBody EmailCertifyRequest request){
        return authService.sendAuth(request);
    }
}
