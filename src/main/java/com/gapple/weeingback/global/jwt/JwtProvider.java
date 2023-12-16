 package com.gapple.weeingback.global.jwt;

 import com.gapple.weeingback.domain.member.entity.AccessRole;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import jakarta.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.GrantedAuthority;
 import org.springframework.stereotype.Component;
 import java.util.Collection;
 import java.util.Date;

 import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

 @Component
 public class JwtProvider {
     private String secretKey;
     private Long expired;

     public JwtProvider(@Value("${jwt.secret}") String secretKey,
                        @Value("${jwt.expired}") Long expired){
        this.secretKey = secretKey;
        this.expired = expired;
     }

     public String generateToken(Authentication authentication) {
         return generateToken(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
     }

     public String generateToken(String username, String role) {
         return Jwts.builder()
                 .setSubject(username)
                 .claim("role", role)
                 .setExpiration(getExpireDate())
                 .signWith(SignatureAlgorithm.HS256, secretKey)
                 .compact();
     }

     private Date getExpireDate() {
         Date now = new Date();
         return new Date(now.getTime() + expired);
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
