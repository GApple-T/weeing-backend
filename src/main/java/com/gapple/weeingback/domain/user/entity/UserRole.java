package com.gapple.weeingback.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserRole {
  DEFAULT("ROLE_DEFAULT"),
  STUDENT("ROLE_STUDENT"),
  TEACHER("ROLE_TEACHER"),
  COMPLEX("ROLE_COMPLEX"),
  ADMIN("ROLE_ADMIN");

  private String roleName;
}
