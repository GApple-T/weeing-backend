 package com.gapple.weeingback.global.jwt;

 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.security.Keys;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.Authentication;
 import org.springframework.stereotype.Component;

 import java.util.*;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

 @Component
 public class JwtProvider {
     private final String secretKey;
     private final Long access;
     private final Long refresh;

     public JwtProvider(@Value("${jwt.secret}") String secretKey,
                        @Value("${jwt.access}") Long access,
                        @Value("${jwt.refresh}") Long refresh){
        this.secretKey = secretKey;
        this.access = access;
        this.refresh = refresh;
     }

     public String generateAccessToken(Authentication authentication) {
         return generateToken(
                 authentication.getPrincipal().toString(),
                 authentication.getAuthorities().toString(),
                 access
         );
     }

     public String generateRefreshToken(Authentication authentication) {
         return generateToken(
                 authentication.getPrincipal().toString(),
                 authentication.getAuthorities().toString(),
                 refresh
         );
     }

     public String generateToken(String username, String role, Long expired) {
         Long now = new Date().getTime();

         return Jwts.builder()
                 .setSubject(username)
                 .claim("role", role)
                 .setExpiration(new Date(now + expired))
                 .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                 .compact();
     }

     public String resolveToken(String token) {
         if(token != null && token.startsWith("Bearer ")){
            return token.substring(7);
         } else return null;
     }

     public Authentication getAuthentication(String accessToken) {
         return new UsernamePasswordAuthenticationToken(getUsername(accessToken), "", createAuthorityList(getRole(accessToken)));
     }

     private String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
     }

     private String getRole(String token) {
        String role = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);

        return role;
     }

     public boolean validateToken(String token) {
         if (token == null) {
             return false;
         }
         try {
             return Jwts.parserBuilder()
                     .setSigningKey(secretKey.getBytes())
                     .build()
                     .parseClaimsJws(token)
                     .getBody()
                     .getExpiration()
                     .after(new Date());
         }
         catch (Exception e) {
             return false;
         }
     }
 }
