package com.gapple.weeingback.domain.auth.service.impl;

import com.gapple.weeingback.domain.auth.dto.*;
import com.gapple.weeingback.domain.auth.repository.RefreshTokenRepository;
import com.gapple.weeingback.domain.auth.service.AuthService;
import com.gapple.weeingback.domain.member.entity.AccessRole;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.email.service.impl.EmailServiceImpl;
import com.gapple.weeingback.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final EmailServiceImpl emailService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

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
        } else throw new RuntimeException();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<AuthLoginResponse> login(AuthLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail());

        if(passwordEncoder.matches(request.getPassword(), member.getPassword())){
            String id = member.getId().toString();
            String password = member.getPassword();

            List<AccessRole> roles = new ArrayList<>();
            roles.add(AccessRole.valueOf(member.getRole()));

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(id, password, roles);
            String access = jwtProvider.generateAccessToken(authentication);
            String refresh = jwtProvider.generateRefreshToken(authentication);
            return ResponseEntity.ok(new AuthLoginResponse(access, refresh, "ok"));
        } else throw new IllegalArgumentException();
    }

    public ResponseEntity<?> logout(String authentication, String refresh) {
        refresh = jwtProvider.resolveToken(refresh);

        if(jwtProvider.validateToken(refresh)){
            Authentication refreshToken = jwtProvider.getAuthentication(refresh);
            UUID memberId = UUID.fromString(refreshToken.getName());
            RefreshTokenrefreshTokenRepository.findRefreshTokenById(memberId);

            SecurityContextHolder.getContext().getAuthentication().getName()
            return new ResponseEntity<>(HttpStatus.OK);
        }else throw new RuntimeException();
    }



    @Override
    public ResponseEntity<String> sendAuth(EmailCertifyRequest request) {
        return ResponseEntity.ok(emailService.sendMail(request.getEmail()));
    }
}
