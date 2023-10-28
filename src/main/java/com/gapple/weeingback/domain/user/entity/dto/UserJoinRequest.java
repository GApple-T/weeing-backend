package com.gapple.weeingback.domain.user.entity.dto;

import com.gapple.weeingback.domain.user.entity.UserRole;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    private String name;
    @Email
    private String email;
    private String password;
//    private UserRole userRole;
}