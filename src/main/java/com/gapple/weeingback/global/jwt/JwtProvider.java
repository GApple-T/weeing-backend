 package com.gapple.weeingback.global.jwt;

 import com.gapple.weeingback.domain.auth.dto.TokenResponse;
 import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import io.jsonwebtoken.io.Encoders;
 import io.jsonwebtoken.security.Keys;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.stereotype.Component;

 import java.sql.Date;
 import java.time.Instant;
 import java.time.temporal.ChronoUnit;

 @Component
 public class JwtProvider {
     private String secret;

     public JwtProvider(@Value("${jwt.secret}") String secret){
        this.secret = secret;
     }

     public TokenResponse generateTokens(String email){
         return new TokenResponse(generateToken(email), generateToken(email));
     }
   public String generateToken(String email){
       Instant issuedAt = Instant.now();
       Instant expirtion = issuedAt.plus(JwtProperties.EXPIRED, ChronoUnit.SECONDS);
       return Jwts.builder()
               .signWith(Keys.hmacShaKeyFor(secret.getBytes()),SignatureAlgorithm.HS256)
               .setSubject(email)
               .setIssuedAt(Date.from(issuedAt))
               .setExpiration(Date.from(expirtion))
               .compact();
   }

     public Claims getAuthentication(String token) {
         return Jwts.parserBuilder()
                 .setSigningKey(secret.getBytes())
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
     }

     public String getSecret() {
         return secret;
     }
 }
