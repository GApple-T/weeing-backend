 package com.gapple.weeingback.global.jwt;

 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;

 import java.sql.Date;
 import java.time.Instant;
 import java.time.temporal.ChronoUnit;

 public class JwtGenerator {
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
 }
