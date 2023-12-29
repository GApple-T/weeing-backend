package com.gapple.weeingback.domain.auth.service;

import com.gapple.weeingback.domain.auth.domain.dto.request.AuthJoinRequest;
import com.gapple.weeingback.domain.auth.domain.dto.request.AuthLoginRequest;
import com.gapple.weeingback.domain.auth.domain.dto.request.EmailCertifyRequest;
import com.gapple.weeingback.domain.auth.domain.dto.response.AuthLoginResponse;
import com.gapple.weeingback.domain.auth.domain.dto.response.AuthLogoutResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    void join(AuthJoinRequest request);
    AuthLoginResponse login(AuthLoginRequest request);
    void logout(String headerAuthorization, String headerRefresh);
    AuthLogoutResponse refresh(String headerAuthorization, String headerRefresh);
    ResponseEntity sendAuth(EmailCertifyRequest request);
}
