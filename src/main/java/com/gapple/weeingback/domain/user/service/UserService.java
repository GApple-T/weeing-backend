package com.gapple.weeingback.domain.user.service;

import com.gapple.weeingback.domain.user.entity.dto.UserJoinRequest;
import com.gapple.weeingback.domain.user.entity.dto.UserLoginRequest;

public interface UserService {
    void join(UserJoinRequest request) throws Exception;
    String login(UserLoginRequest request);
}
