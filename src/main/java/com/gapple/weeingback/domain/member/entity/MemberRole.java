package com.gapple.weeingback.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {
  DEFAULT("ROLE_DEFAULT"),
  STUDENT("ROLE_STUDENT"),
  TEACHER("ROLE_TEACHER"),
  COMPLEX("ROLE_COMPLEX"),
  ADMIN("ROLE_ADMIN");

  private String roleName;
}
