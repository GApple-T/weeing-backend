 package com.gapple.weeingback.global.filter;

 import com.gapple.weeingback.domain.user.entity.User;
 import com.gapple.weeingback.global.jwt.JwtParser;
 import com.gapple.weeingback.global.jwt.JwtProperties;
 import com.gapple.weeingback.global.jwt.userDetail.UserDetail;
 import com.gapple.weeingback.global.jwt.userDetail.UserDetailService;
 import jakarta.servlet.FilterChain;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import lombok.RequiredArgsConstructor;
 import org.springframework.security.authentication.BadCredentialsException;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.web.filter.OncePerRequestFilter;

 import java.io.IOException;

 @RequiredArgsConstructor
 public class JwtFilter extends OncePerRequestFilter {
     private final UserDetailService service;

     @Override
     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String header = JwtProperties.HEADER;

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
         String email = JwtParser.getTokenSubjectOrNull(accessToken);
         if (email == null) {
             throw new BadCredentialsException("만료되거나 유효하지 않은 JWT");
         }
         return email;
     }

     private Authentication createAuthenticationToken(UserDetails userDetails){
        return null;
        // TODO Authentication 객체 만들어서 반환하기
     }
 }