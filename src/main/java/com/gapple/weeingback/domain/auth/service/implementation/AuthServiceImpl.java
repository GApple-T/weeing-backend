package com.gapple.weeingback.domain.auth.service.implementation;

import com.gapple.weeingback.domain.auth.domain.dto.request.AuthJoinRequest;
import com.gapple.weeingback.domain.auth.domain.dto.request.AuthLoginRequest;
import com.gapple.weeingback.domain.auth.domain.dto.request.EmailCertifyRequest;
import com.gapple.weeingback.domain.auth.domain.dto.response.AuthLoginResponse;
import com.gapple.weeingback.domain.auth.domain.dto.response.AuthLogoutResponse;
import com.gapple.weeingback.domain.auth.service.AuthService;
import com.gapple.weeingback.domain.member.entity.AccessRole;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.email.service.EmailService;
import com.gapple.weeingback.global.exception.*;
import com.gapple.weeingback.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final EmailService emailService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final Jedis jedis;

    @Transactional
    public void join(AuthJoinRequest request){
        if(!memberRepository.existsMemberByEmail(request.getEmail())) {
            Member member = Member.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .grade(request.getGrade())
                    .classroom(request.getClassroom())
                    .number(request.getNumber())
                    .role(AccessRole.ROLE_STUDENT.getName())
                    .build();

            memberRepository.save(member);
        } else throw new MemberExistsException();
    }

    @Transactional
    public AuthLoginResponse login(AuthLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new PasswordNotMatchException();
      
        String id = member.getId().toString();
        String password = member.getPassword();

        List<AccessRole> roles = new ArrayList<>();
        roles.add(AccessRole.valueOf(member.getRole()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(id, password, roles);
        String access = jwtProvider.generateAccessToken(authentication);
        String refresh = jwtProvider.generateRefreshToken(authentication);

        jedis.set(id, refresh);

        return new AuthLoginResponse(access, refresh);
    }

    @Transactional
    public void logout(String headerAuthorization, String headerRefresh) {

        if(headerRefresh.isEmpty() || headerAuthorization.isEmpty()){
            throw new TokenNotFoundException();
        }
        String refresh = jwtProvider.resolveToken(headerRefresh);

            Authentication refreshToken = jwtProvider.getAuthentication(refresh);
            String refreshKey = refreshToken.getName();

        String savedRefresh = jedis.get(refreshKey);

        if(refresh.equals(savedRefresh)){
            jedis.set(refreshKey, "");
        } else throw new TokenNotEqualsException();
    }

    @Transactional
    public AuthLogoutResponse refresh(String headerAuthorization, String headerRefresh){
        if(headerRefresh.isEmpty() || headerAuthorization.isEmpty()){
            throw new TokenNotFoundException();
        }
        String refresh = jwtProvider.resolveToken(headerRefresh);
        String authorization = jwtProvider.resolveToken(headerAuthorization);

        boolean accessValidate = jwtProvider.validateToken(authorization);
        boolean refreshValidate = jwtProvider.validateToken(refresh);

        UUID savedId;
        if(refreshValidate){
                Authentication refreshToken = jwtProvider.getAuthentication(refresh);
                savedId = UUID.fromString(refreshToken.getName());
        } else savedId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());

        String token = jedis.get(savedId.toString());

        if(refresh.equals(token)){
            if(!accessValidate && !refreshValidate){
                throw new TokenValidateException();
            } else if(!accessValidate){
                Member member = memberRepository.findMemberById(savedId).orElseThrow(MemberNotFoundException::new);

                String password = member.getPassword();

                List<AccessRole> roles = new ArrayList<>();
                roles.add(AccessRole.valueOf(member.getRole()));

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(savedId.toString(), password, roles);

                String newAccessToken = jwtProvider.generateAccessToken(authentication);

                return new AuthLogoutResponse(newAccessToken, null);
            } else if(!refreshValidate){
                Authentication authorizationToken = jwtProvider.getAuthentication(authorization);
                UUID id = UUID.fromString(authorizationToken.getName());
                String newRefresh = jwtProvider.generateRefreshToken(authorizationToken);

                jedis.set(id.toString(), newRefresh);

                return new AuthLogoutResponse(null, newRefresh);
            } else {
                return new AuthLogoutResponse(null, null);
            }
        } else throw new TokenNotEqualsException();
    }


    @Override
    public ResponseEntity<String> sendAuth(EmailCertifyRequest request) {
        return ResponseEntity.ok().body(emailService.sendAuth(request.getEmail()));
    }
}
