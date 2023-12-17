package com.gapple.weeingback.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum AccessRole implements GrantedAuthority {
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_TEACHER("ROLE_TEACHER"),
    ROLE_ADMIN("ROLE_ADMIN");

    public String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
