 package com.gapple.weeingback.global.filter;

 import com.gapple.weeingback.global.jwt.JwtProperties;
 import com.gapple.weeingback.global.jwt.JwtProvider;
 import com.gapple.weeingback.global.jwt.userDetails.UserDetailsServiceImpl;
 import jakarta.servlet.FilterChain;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.security.authentication.BadCredentialsException;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.web.filter.OncePerRequestFilter;

 import java.io.IOException;

 @Slf4j
 @RequiredArgsConstructor
 public class JwtFilter extends OncePerRequestFilter {
     private final UserDetailsServiceImpl service;

     @Override
     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String header = request.getHeader("Authorization");
         log.warn(header);

         if(isNullOrWrong(header)){
             super.doFilter(request, response, filterChain);
         }

         String accessToken = extractAccessToken(header);
         String email = getEmailFromToken(accessToken);

         UserDetails userDetails = service.loadUserByUsername(email);

         Authentication authentication = createAuthenticationToken(userDetails);

         SecurityContextHolder.clearContext();
         SecurityContextHolder.getContext().setAuthentication(authentication);

         super.doFilter(request, response, filterChain);
     }

     private boolean isNullOrWrong(String header) {
         return header == null || !header.startsWith(JwtProperties.PREFIX);
     }

     private String extractAccessToken(String jwtTokenHeader) {
         return jwtTokenHeader.replace(JwtProperties.PREFIX, "");
     }

     private String getEmailFromToken(String accessToken) {
         String email = JwtProvider.getTokenSubjectOrNull(accessToken);
         if (email == null) {
             throw new BadCredentialsException("만료되거나 유효하지 않은 JWT");
         }
         return email;
     }

     private Authentication createAuthenticationToken(UserDetails userDetails){
         return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
        // TODO Authentication 객체 만들어서 반환하기
     }
 }