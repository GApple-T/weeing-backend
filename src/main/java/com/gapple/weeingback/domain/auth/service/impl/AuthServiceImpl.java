package com.gapple.weeingback.domain.auth.service.impl;

import com.gapple.weeingback.domain.auth.dto.*;
import com.gapple.weeingback.domain.auth.service.AuthService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<?> join(MemberJoinRequest req){
        if(!memberRepository.existsMemberByEmail(req.getEmail())) {
            Member member = Member.builder()
                    .email(req.getEmail())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .build();

            memberRepository.save(member);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new RuntimeException();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<TokenResponse> login(MemberLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail());

        if(passwordEncoder.matches(request.getPassword(), member.getPassword())){
            return ResponseEntity.ok(jwtProvider.generateTokens(request.getEmail()));
        } else throw new RuntimeException();
    }

    @Override
    public ResponseEntity<TokenResponse> refresh(TokenRequest tokenRequest) {
        return null;
    }
}
