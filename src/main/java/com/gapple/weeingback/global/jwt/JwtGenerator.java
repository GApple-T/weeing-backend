 package com.gapple.weeingback.global.jwt;

 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;

 import java.sql.Date;
 import java.time.Instant;
 import java.time.temporal.ChronoUnit;

 public class JwtGenerator {
   public static TokenResponse generateToken(Long userId){
       return new TokenResponse(
               generateAccessToken(userId)
       );
   }

   private static String generateAccessToken(Long userId){
       Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
       Instant expirtion = issuedAt.plus(JwtProperties.EXPIRED, ChronoUnit.SECONDS);
       return Jwts.builder()
               .signWith(JwtProperties.SECRET, SignatureAlgorithm.HS256)
               .setSubject(userId.toString())
               .setIssuedAt(Date.from(issuedAt))
               .setExpiration(Date.from(expirtion))
               .compact();
   }
 }
