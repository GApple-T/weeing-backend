package com.gapple.weeingback.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AuthLoginResponse {
    private String token;

    private String success;

    private String error;
}
