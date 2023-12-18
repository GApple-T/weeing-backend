package com.gapple.weeingback.domain.auth.controller;

import com.gapple.weeingback.domain.auth.dto.*;
import com.gapple.weeingback.domain.auth.service.AuthService;
<<<<<<< HEAD
import jakarta.servlet.http.HttpServletRequest;
=======
>>>>>>> 956c9c58831b73713b51ee24452822b540a923b6
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
    public ResponseEntity<?> login(@Valid @RequestBody AuthLoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestHeader(name = "Authorization", required = true) String authorization,
                                    @Valid @RequestHeader(name = "refresh-token", required = true) String refresh){
        return authService.logout(authorization, refresh);
    }

    @PostMapping("/send-auth")
    public ResponseEntity<?> emailCertify(@Valid @RequestBody EmailCertifyRequest request){
        return authService.sendAuth(request);
    }
}
