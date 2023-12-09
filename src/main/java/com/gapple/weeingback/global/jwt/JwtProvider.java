 package com.gapple.weeingback.global.jwt;

 import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.ExpiredJwtException;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import io.jsonwebtoken.impl.DefaultJwtBuilder;
 import io.jsonwebtoken.io.Decoders;
 import io.jsonwebtoken.io.Encoders;
 import io.jsonwebtoken.security.Keys;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.stereotype.Component;

 import java.nio.charset.StandardCharsets;
 import java.security.Key;
 import java.sql.Date;
 import java.time.Instant;
 import java.time.temporal.ChronoUnit;
 import java.util.Base64;

 @Component
 public class JwtProvider {
     private String secret;

     public JwtProvider(@Value("${jwt.secret}") String secret){
        this.secret = secret;
     }

   public String generateToken(String email){
       Instant issuedAt = Instant.now();
       Instant expirtion = issuedAt.plus(JwtProperties.EXPIRED, ChronoUnit.SECONDS);
       return Jwts.builder()
               .signWith(SignatureAlgorithm.HS256, Encoders.BASE64.encode(secret.getBytes()))
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
