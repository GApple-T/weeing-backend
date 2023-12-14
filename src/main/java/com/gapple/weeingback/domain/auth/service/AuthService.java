package com.gapple.weeingback.domain.auth.service;

import com.gapple.weeingback.domain.auth.dto.*;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity join(MemberJoinRequest request);
    ResponseEntity login(MemberLoginRequest request);
    ResponseEntity<TokenResponse> refresh(TokenRequest request);
}
