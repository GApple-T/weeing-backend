package com.gapple.weeingback.domain.auth.service.implementation;

import com.gapple.weeingback.domain.auth.domain.dto.*;
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
    private final StringRedisTemplate stringRedisTemplate;

    @Transactional
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

    @Transactional
    public ResponseEntity<AuthLoginResponse> login(AuthLoginRequest request){
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

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(id, refresh);

        return ResponseEntity.ok(new AuthLoginResponse(access, refresh, "ok"));
    }

    @Transactional
    public ResponseEntity<?> logout(String headerAuthorization, String headerRefresh) {

        if(headerRefresh.isEmpty() || headerAuthorization.isEmpty()){
            throw new TokenNotFoundException();
        }
        String refresh = jwtProvider.resolveToken(headerRefresh);

            Authentication refreshToken = jwtProvider.getAuthentication(refresh);
            String refreshKey = refreshToken.getName();

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String savedRefresh = stringValueOperations.get(refreshKey);

        if(refresh.equals(savedRefresh)){
            stringValueOperations.set(refreshKey.toString(), "");
        } else throw new TokenNotEqualsException();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<AuthLogoutResponse> refresh(String headerAuthorization, String headerRefresh){
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

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String token = stringValueOperations.get(savedId.toString());

        if(refresh.equals(token)){
            if(!accessValidate && !refreshValidate){
                throw new TokenValidateException();
            } else if(!accessValidate){
                Member member = memberRepository.findMemberById(savedId);

                String password = member.getPassword();

                List<AccessRole> roles = new ArrayList<>();
                roles.add(AccessRole.valueOf(member.getRole()));

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(savedId.toString(), password, roles);

                String newAccessToken = jwtProvider.generateAccessToken(authentication);

                return ResponseEntity.ok(new AuthLogoutResponse(newAccessToken, null, "ok"));
            } else if(!refreshValidate){
                Authentication authorizationToken = jwtProvider.getAuthentication(authorization); // 오류 발생지
                UUID id = UUID.fromString(authorizationToken.getName());
                String newRefresh = jwtProvider.generateRefreshToken(authorizationToken);

                stringValueOperations.set(id.toString(), newRefresh);

                return ResponseEntity.ok(new AuthLogoutResponse(null, newRefresh, "ok"));
            } else {
                return ResponseEntity.ok().body(new AuthLogoutResponse(null, null, "ok"));
            }
        } else throw new TokenNotEqualsException();
    }


    @Override
    public ResponseEntity<String> sendAuth(EmailCertifyRequest request) {
        return ResponseEntity.ok().body(emailService.sendAuth(request.getEmail()));
    }
}
