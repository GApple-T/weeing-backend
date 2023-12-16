package com.gapple.weeingback.domain.auth.service.impl;

import com.gapple.weeingback.domain.auth.dto.*;
import com.gapple.weeingback.domain.auth.service.AuthService;
import com.gapple.weeingback.domain.member.entity.AccessRole;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.email.service.impl.EmailServiceImpl;
import com.gapple.weeingback.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final EmailServiceImpl emailService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<AuthJoinResponse> join(AuthJoinRequest req){
        if(!memberRepository.existsMemberByEmail(req.getEmail())) {
            Member member = Member.builder()
                    .email(req.getEmail())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .name(req.getName())
                    .number(req.getNumber())
                    .role(AccessRole.STUDENT.getName())
                    .build();

            memberRepository.save(member);
            return ResponseEntity.ok(new AuthJoinResponse("ok"));
        } else throw new RuntimeException();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<AuthLoginResponse> login(AuthLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail());

        if(passwordEncoder.matches(request.getPassword(), member.getPassword())){
            String id = member.getId().toString();
            String role = member.getRole().toString();

            log.info(id + " " + role);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(id, role);
            String token = jwtProvider.generateToken(authentication);
            return ResponseEntity.ok(new AuthLoginResponse(token, "ok"));
        } else throw new IllegalArgumentException();
    }

    @Override
    public ResponseEntity sendAuth(EmailCertifyRequest request) {
        return ResponseEntity.ok(emailService.sendMail(request.getEmail()));
    }
}
