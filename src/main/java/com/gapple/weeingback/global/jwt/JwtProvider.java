 package com.gapple.weeingback.global.jwt;

 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.Authentication;
 import org.springframework.stereotype.Component;

 import java.util.Date;

 import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

 @Component
 public class JwtProvider {
     private String secretKey;
     private Long access;
     private Long refresh;

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
                 authentication.getCredentials().toString(),
                 getAccessExpireDate()
         );
     }

     public String generateRefreshToken(Authentication authentication) {
         return generateToken(
                 authentication.getPrincipal().toString(),
                 authentication.getCredentials().toString(),
                 getRefreshExpireDate()
         );
     }

     public String generateToken(String username, String role, Date expired) {
         return Jwts.builder()
                 .setSubject(username)
                 .claim("role", role)
                 .setExpiration(expired)
                 .signWith(SignatureAlgorithm.HS256, secretKey)
                 .compact();
     }

     private Date getAccessExpireDate() {
         Date now = new Date();
         return new Date(now.getTime() + access);
     }

     private Date getRefreshExpireDate() {
         Date now = new Date();
         return new Date(now.getTime() + refresh);
     }

     public String resolveToken(String token) {
         if(token != null && token.startsWith("Bearer ")){
            return token.substring(7);
         } else return null;
     }

     public Authentication getAuthentication(String accessToken) {
         return new UsernamePasswordAuthenticationToken(getUsername(accessToken), "", createAuthorityList(getRole(accessToken)));
     }

     private String getUsername(String accessToken) {
         return Jwts.parser()
                 .setSigningKey(secretKey)
                 .parseClaimsJws(accessToken)
                 .getBody()
                 .getSubject();
     }

     private String getRole(String accessToken) {
         return (String) Jwts.parser()
                 .setSigningKey(secretKey)
                 .parseClaimsJws(accessToken)
                 .getBody()
                 .get("role", String.class);
     }

     public boolean validateToken(String accessToken) {
         if (accessToken == null) {
             return false;
         }

         try {
             return Jwts.parser()
                     .setSigningKey(secretKey)
                     .parseClaimsJws(accessToken)
                     .getBody()
                     .getExpiration()
                     .after(new Date());
         }
         catch (Exception e) {
             return false;
         }
     }
 }
