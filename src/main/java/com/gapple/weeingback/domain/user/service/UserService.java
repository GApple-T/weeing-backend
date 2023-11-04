package com.gapple.weeingback.domain.user.service;

import com.gapple.weeingback.domain.user.entity.dto.UserJoinRequest;
import com.gapple.weeingback.domain.user.entity.dto.UserLoginRequest;
import com.gapple.weeingback.global.jwt.TokenResponse;

public interface UserService {
    void join(UserJoinRequest request) throws Exception;
    TokenResponse login(UserLoginRequest request) throws IllegalAccessException;
}
