package com.gapple.weeingback.domain.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequest {
  private String email;
  private String password;
}
