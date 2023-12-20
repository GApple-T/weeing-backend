package com.gapple.weeingback.domain.auth.service;

import com.gapple.weeingback.domain.auth.domain.dto.*;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthJoinResponse> join(AuthJoinRequest request);
    ResponseEntity login(AuthLoginRequest request);
    ResponseEntity logout(String headerAuthorization, String headerRefresh);
    ResponseEntity<AuthLogoutResponse> refresh(String headerAuthorization, String headerRefresh);
    ResponseEntity sendAuth(EmailCertifyRequest request);
}
