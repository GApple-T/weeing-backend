package com.gapple.weeingback.global.jwt;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtProperties {
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final int EXPIRED = 86400;
    public static final String SECRET_VALUE = "2g23gjklhgl2h2rj2hHEjkg4h322h24jhtjb2y92hrblg2hbrhb2gkjhb20r2bhkjrh2lkg2k232kgjhllh";
    public static final SecretKey SECRET = Keys.hmacShaKeyFor(SECRET_VALUE.getBytes(StandardCharsets.UTF_8));
}
