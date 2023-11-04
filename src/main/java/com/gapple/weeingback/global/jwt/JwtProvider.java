 package com.gapple.weeingback.global.jwt;

 import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.ExpiredJwtException;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import org.springframework.stereotype.Component;

 import java.security.Key;
 import java.sql.Date;
 import java.time.Instant;
 import java.time.temporal.ChronoUnit;

 public class JwtProvider {
   public static TokenResponse generateToken(String email){
       return new TokenResponse(
               generateAccessToken(email)
       );
   }

   private static String generateAccessToken(String email){
       Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
       Instant expirtion = issuedAt.plus(JwtProperties.EXPIRED, ChronoUnit.SECONDS);
       return Jwts.builder()
               .signWith(JwtProperties.SECRET, SignatureAlgorithm.HS256)
               .setSubject(email)
               .setIssuedAt(Date.from(issuedAt))
               .setExpiration(Date.from(expirtion))
               .compact();
   }

     public static String getTokenSubjectOrNull(String token) {
         try {
             return getAuthentication(token, JwtProperties.SECRET).getSubject();
         } catch (ExpiredJwtException e) {
             return null;
         }
     }

     private static Claims getAuthentication(String token, Key secret) {
         return Jwts.parserBuilder()
                 .setSigningKey(secret)
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
     }
 }
