package com.gapple.weeingback.domain.auth.service.implementation;

import com.gapple.weeingback.domain.auth.domain.dto.*;
import com.gapple.weeingback.domain.auth.service.AuthService;
import com.gapple.weeingback.domain.member.entity.AccessRole;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.email.service.EmailService;
import com.gapple.weeingback.global.exception.MemberExistsException;
import com.gapple.weeingback.global.exception.PasswordNotMatchException;
import com.gapple.weeingback.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final EmailService emailService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final StringRedisTemplate stringRedisTemplate;

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

    @Transactional
    public ResponseEntity<AuthLoginResponse> login(AuthLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail())
                .orElseThrow(RuntimeException::new);

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

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<?> logout(String headerAuthorization, String headerRefresh) {

        if(headerRefresh == null){
            throw new RuntimeException();
        }
        String refresh = jwtProvider.resolveToken(headerRefresh);

        if(jwtProvider.validateToken(refresh)){
            Authentication refreshToken = jwtProvider.getAuthentication(refresh);
            UUID refreshKey = UUID.fromString(refreshToken.getName());

            ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
            String tokenValue = stringValueOperations.get(refreshKey.toString());
//            RefreshToken savedRefreshToken = refreshTokenRepository.findRefreshTokenByKey(refreshKey);


            if(jwtProvider.validateToken(tokenValue) &&
                    refresh.equals(tokenValue)){
//                refreshTokenRepository.delete(savedRefreshToken);
                stringValueOperations.decrement(refreshKey.toString());
            } else throw new RuntimeException();

            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new RuntimeException();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<AuthLogoutResponse> refresh(String refresh){
        if(refresh == null){
            throw new RuntimeException();
        }

        refresh = jwtProvider.resolveToken(refresh);

        Authentication token = jwtProvider.getAuthentication(refresh);
        String key = token.getName();

        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String value = operations.get(key);

        if(!jwtProvider.validateToken(value) && !refresh.equals(value)) {
            throw new RuntimeException();
        }

        Member member = memberRepository.findMemberById(UUID.fromString(key));

        String id = member.getId().toString();
        String password = member.getPassword();

        List<AccessRole> roles = new ArrayList<>();
        roles.add(AccessRole.valueOf(member.getRole()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(id, password, roles);

        String newAccess = jwtProvider.generateAccessToken(authentication);
        String newRefresh = jwtProvider.generateRefreshToken(authentication);

        operations.set(id, newRefresh);

        return ResponseEntity.ok(new AuthLogoutResponse(newAccess, newRefresh, "ok"));
    }


    @Override
    public ResponseEntity<String> sendAuth(EmailCertifyRequest request) {
        return ResponseEntity.ok().body(emailService.sendAuth(request.getEmail()));
    }
}
