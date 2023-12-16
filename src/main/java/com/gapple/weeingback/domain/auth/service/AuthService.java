package com.gapple.weeingback.domain.auth.service;

import com.gapple.weeingback.domain.auth.dto.*;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthJoinResponse> join(AuthJoinRequest request);
    ResponseEntity login(AuthLoginRequest request);

    ResponseEntity sendAuth(EmailCertifyRequest request);
}
