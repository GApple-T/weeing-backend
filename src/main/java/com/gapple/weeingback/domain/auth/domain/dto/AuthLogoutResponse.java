package com.gapple.weeingback.domain.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthLogoutResponse {
    private String access;

    private String refresh;

    private String success;
}
