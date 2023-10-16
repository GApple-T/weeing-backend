package com.gapple.weeingback.domain.user.entity.dto;

import com.gapple.weeingback.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    private String name;
    private String email;
    private String password;
//    private UserRole userRole;
}