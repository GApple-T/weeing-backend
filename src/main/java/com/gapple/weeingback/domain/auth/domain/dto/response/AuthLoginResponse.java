package com.gapple.weeingback.domain.auth.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthLoginResponse {
    private String access;

    private String refresh;
}
