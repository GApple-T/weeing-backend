package com.gapple.weeingback.domain.member.service;

import com.gapple.weeingback.domain.member.entity.dto.MemberJoinRequest;
import com.gapple.weeingback.domain.member.entity.dto.MemberLoginRequest;
import com.gapple.weeingback.global.jwt.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity join(MemberJoinRequest request) throws Exception;
    ResponseEntity<TokenResponse> login(MemberLoginRequest request) throws IllegalAccessException;
}
