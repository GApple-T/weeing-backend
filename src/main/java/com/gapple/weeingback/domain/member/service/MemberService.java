package com.gapple.weeingback.domain.member.service;

import com.gapple.weeingback.domain.member.entity.dto.MemberJoinRequest;
import com.gapple.weeingback.domain.member.entity.dto.MemberLoginRequest;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity join(MemberJoinRequest request) throws Exception;
    ResponseEntity<String> login(MemberLoginRequest request) throws IllegalAccessException;
}
