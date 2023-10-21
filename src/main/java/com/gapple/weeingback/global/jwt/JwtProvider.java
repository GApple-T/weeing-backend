// package com.gapple.weeingback.global.jwt;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.security.core.Authentication;

// import java.security.Key;
// import java.util.Date;

// public class JwtProvider {
//   private String secret = "1234lbvyv1giuyr1fvbh3uy1jrgbv";
//   private Key key;

//   public JwtProvider(){

 
//   }

//   public String generateToken(String email, String type, Long expired){
//     String token = Jwts.builder()
//         .setSubject(type)
//         .setAudience(email)
//         .setExpiration(new Date(System.currentTimeMillis() + 1000L))
//         .setIssuedAt(new Date(System.currentTimeMillis()))
// //        .signWith(new Key, SignatureAlgorithm.HS256)
//         .compact();
//     return token;
//   }

//   public Authentication getAuthentication(String token){

//   }
// }
