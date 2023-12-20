package com.gapple.weeingback.domain.auth.service.implementation;

import com.gapple.weeingback.domain.auth.domain.RefreshToken;
import com.gapple.weeingback.domain.auth.domain.dto.*;
import com.gapple.weeingback.domain.auth.repository.RefreshTokenRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
    private final EmailService emailService;
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
        } else throw new MemberExistsException();
    }

    @Transactional
    public ResponseEntity<AuthLoginResponse> login(AuthLoginRequest request){
        Member member = memberRepository.findMemberByEmail(request.getEmail())
                .orElseThrow(RuntimeException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new PasswordNotMatchException();
      
        UUID id = member.getId();
        String password = member.getPassword();

        List<AccessRole> roles = new ArrayList<>();
        roles.add(AccessRole.valueOf(member.getRole()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(id, password, roles);
        String access = jwtProvider.generateAccessToken(authentication);
        String refresh = jwtProvider.generateRefreshToken(authentication);

        if(refreshTokenRepository.existsByKey(id)){
            RefreshToken savedToken = refreshTokenRepository.findRefreshTokenByKey(id);
            refreshTokenRepository.delete(savedToken);
        }

        RefreshToken refreshToken = RefreshToken.builder()
            .key(id)
            .value(refresh)
            .build();

        refreshTokenRepository.save(refreshToken);

        return ResponseEntity.ok(new AuthLoginResponse(access, refresh, "ok"));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<?> logout(String headerAuthorization, String headerRefresh) {
        if(headerRefresh == null){
            throw new RuntimeException();
        }
        String refresh = jwtProvider.resolveToken(headerRefresh);

            Authentication refreshToken = jwtProvider.getAuthentication(refresh);
            UUID refreshKey = UUID.fromString(refreshToken.getName());

            RefreshToken savedRefreshToken = refreshTokenRepository.findRefreshTokenByKey(refreshKey);
            String savedRefresh = savedRefreshToken.getValue();

            if(refresh.equals(savedRefresh)){
                refreshTokenRepository.delete(savedRefreshToken);
            } else throw new RuntimeException();

            return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseEntity<AuthLogoutResponse> refresh(String headerAuthorization, String headerRefresh){
        if(headerRefresh == null){
            throw new RuntimeException();
        }
        String refresh = jwtProvider.resolveToken(headerRefresh);
        String authorization = jwtProvider.resolveToken(headerAuthorization);
        log.info("Access={}, Refresh={}", authorization, refresh);
        log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());

        boolean accessValidate = jwtProvider.validateToken(authorization);
        boolean refreshValidate = jwtProvider.validateToken(refresh);

        UUID savedId;
        if(refreshValidate){
                Authentication refreshToken = jwtProvider.getAuthentication(refresh);
                log.info("Authorities={}", refreshToken.getAuthorities());
                savedId = UUID.fromString(refreshToken.getName());
        } else savedId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("savedId={}", savedId);

        RefreshToken savedToken =
                refreshTokenRepository.findRefreshTokenByKey(savedId);

        log.info("savedToken={}", savedToken.getValue());

        if(refresh.equals(savedToken.getValue())){
            if(!accessValidate && !refreshValidate){
                throw new RuntimeException();
            } else if(!accessValidate){
                Member member = memberRepository.findMemberById(savedId);

                log.info(member.getId().toString());

                String password = member.getPassword();

                List<AccessRole> roles = new ArrayList<>();
                roles.add(AccessRole.valueOf(member.getRole()));

                log.info(roles.toString());

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(savedId.toString(), password, roles);

                log.info(authentication.getAuthorities().toString());

                String newAccessToken = jwtProvider.generateAccessToken(authentication);

                return ResponseEntity.ok(new AuthLogoutResponse(newAccessToken, null, "ok"));
            } else if(!refreshValidate){
                log.info("authorization={}", authorization);
                Authentication authorizationToken = jwtProvider.getAuthentication(authorization); // 오류 발생지
                log.info("authorizationToken={}", authorizationToken);
                UUID id = UUID.fromString(authorizationToken.getName());
                log.info("id={}", id);
                String newRefresh = jwtProvider.generateRefreshToken(authorizationToken);
                log.info("newRefresh={}",newRefresh);

                RefreshToken newRefreshToken = RefreshToken.builder()
                        .key(id)
                        .value(newRefresh)
                        .build();
                log.info("newRefreshToken={}", newRefreshToken);

                refreshTokenRepository.delete(savedToken);
                refreshTokenRepository.save(newRefreshToken);

                return ResponseEntity.ok(new AuthLogoutResponse(null, newRefresh, "ok"));
            } else {
                log.info("ok");
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else throw new RuntimeException();
    }


    @Override
    public ResponseEntity<String> sendAuth(EmailCertifyRequest request) {
        return ResponseEntity.ok().body(emailService.sendAuth(request.getEmail()));
    }
}
