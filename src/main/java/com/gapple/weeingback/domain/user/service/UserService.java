package com.gapple.weeingback.domain.user.service;

import com.gapple.weeingback.domain.user.entity.dto.UserJoinRequest;
import com.gapple.weeingback.domain.user.entity.dto.UserLoginRequest;
import com.gapple.weeingback.global.jwt.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity join(UserJoinRequest request) throws Exception;
    ResponseEntity<TokenResponse> login(UserLoginRequest request) throws IllegalAccessException;
}
