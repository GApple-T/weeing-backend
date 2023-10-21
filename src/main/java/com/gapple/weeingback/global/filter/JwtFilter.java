// package com.gapple.weeingback.global.filter;

// import com.gapple.weeingback.global.jwt.JwtProvider;
// import jakarta.servlet.*;
// import jakarta.servlet.http.HttpServletRequest;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.util.StringUtils;

// import java.io.IOException;

// @Slf4j
// public class JwtFilter extends GenericFilter {
//   public static final String AUTHORIZATION_HEADER = "Authorization";

//   private final JwtProvider provider;

//   public JwtFilter(JwtProvider provider){
//     this.provider = provider;
//   }

//   @Override
//   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//     System.out.println("doFilter");

//     HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//     String jwt = resolveToken(httpServletRequest);
//     String requestURI = httpServletRequest.getRequestURI();

//     // 토큰 유효성 검증 후 정상이면 SecurityContext에 저장
//     if(StringUtils.hasText(jwt) && provider.validateToken(jwt)){ // TODO 토큰 검증 만들기
//       Authentication authentication = provider.getAuthentication(jwt); // TODO 토큰 인증 주기
//       SecurityContextHolder.getContext().setAuthentication(authentication);
//       log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}",authentication.getName(),requestURI);
//     }

//     else log.debug("유효한 JWT 토큰이 없습니다, uri: {}",requestURI);

//     // 생성한 필터 실행
//     chain.doFilter(httpServletRequest,response);
//   }

//   // Request Header에서 토큰 정보를 꺼내오기
//   private String resolveToken(HttpServletRequest request){
//     String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//     if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){

//       System.out.println("token : " + bearerToken);

//       return bearerToken.substring(7);
//     }
//     return null;
//   }
// }