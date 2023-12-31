package com.gapple.weeingback.domain.member.entity;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NumberNameWithId {
  private Long grade;

  private Long classroom;

  private Long number;

  private String id;
}
