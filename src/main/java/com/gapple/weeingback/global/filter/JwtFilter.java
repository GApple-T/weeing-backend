package com.gapple.weeingback.global.filter;

import com.gapple.weeingback.global.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authorization = request.getHeader("Authorization");
//        System.out.println("그냥 테스트");
//        super.doFilter(request, response, filterChain);
//
//        String username = null;
//        String jwt = null;
//
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            jwt = authorization.substring(7);
//            try {
//                Claims claims = Jwts.parser().setSigningKey(JwtProperties.SECRET_VALUE).parseClaimsJws(jwt).getBody();
//                username = claims.getSubject();
//            } catch (ExpiredJwtException e) {
//                // Token이 만료된 경우
//                logger.warn("JWT token is expired: {}");
//            }
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            // 토큰이 유효하면 Spring Security에 사용자 정보를 설정
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(username, null, null);
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }

        filterChain.doFilter(request, response);
    }
}
