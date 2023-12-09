package com.gapple.weeingback.global.filter;

import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.jwt.JwtProperties;
import com.gapple.weeingback.global.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        Claims authentication = null;
        Member member = null;

        if(header != null && header.startsWith("Bearer ")){
            header.substring(7);
            authentication =  jwtProvider.getAuthentication(request.getHeader("Authorization"));

            member = memberRepository.findMemberByEmail(authentication.getSubject());

            log.info(authentication.toString());
        }

        if(member != null && SecurityContextHolder.getContext() != null){
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authentication.getSubject(), member.getPassword());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}