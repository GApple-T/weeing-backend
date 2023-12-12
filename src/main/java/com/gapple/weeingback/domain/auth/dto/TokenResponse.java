package com.gapple.weeingback.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record TokenResponse(String access, String refresh) {
    public TokenResponse(String access, String refresh) {
        this.access = access;
        this.refresh = refresh;
    }

    @Override
    public String access() {
        return access;
    }

    @Override
    public String refresh() {
        return refresh;
    }
}
