package com.gapple.weeingback.global.jwt;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtProperties {
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer";
    public static final int EXPIRED = 86400;
}
