package com.gapple.weeingback.domain.member.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginRequest {
  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;
}