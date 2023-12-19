package com.gapple.weeingback.domain.auth.service.implementation;

import com.gapple.weeingback.domain.auth.dto.*;
import com.gapple.weeingback.domain.auth.service.AuthService;
import com.gapple.weeingback.domain.member.entity.AccessRole;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.email.service.EmailService;
import com.gapple.weeingback.global.exception.MemberExistsException;
import com.gapple.weeingback.global.exception.MemberNotFoundException;
import com.gapple.weeingback.global.exception.PasswordNotMatchException;
import com.gapple.weeingback.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final EmailService emailService;
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
                    .role(AccessRole.ROLE_STUDENT.getName())
                    .build();

            memberRepository.save(member);
            return ResponseEntity.ok(new AuthJoinResponse("ok"));
        } else throw new MemberExistsException();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<AuthLoginResponse> login(AuthLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new PasswordNotMatchException();

        String id = member.getId().toString();
        String password = member.getPassword();

        List<AccessRole> roles = new ArrayList<>();
        roles.add(AccessRole.valueOf(member.getRole()));

        Authentication authentication =
                    new UsernamePasswordAuthenticationToken(id, password, roles);
        String access = jwtProvider.generateAccessToken(authentication);
        String refresh = jwtProvider.generateRefreshToken(authentication);

        return ResponseEntity.ok(new AuthLoginResponse(access, refresh, "ok"));
    }

    @Override
    public ResponseEntity<String> sendAuth(EmailCertifyRequest request) {
        return ResponseEntity.ok(emailService.sendAuth(request.getEmail()));
    }
}
