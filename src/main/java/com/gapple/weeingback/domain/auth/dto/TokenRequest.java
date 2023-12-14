package com.gapple.weeingback.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record TokenRequest(String access, String refresh) {
    public TokenRequest(String access, String refresh) {
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
