package com.gapple.weeingback.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.security.Key;

public class JwtParser {
    public static String getTokenSubjectOrNull(String token) {
        try {
            return getTokenBody(token, JwtProperties.SECRET).getSubject();
        } catch (ExpiredJwtException e) {
            return null;
        }
    }

    private static Claims getTokenBody(String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
