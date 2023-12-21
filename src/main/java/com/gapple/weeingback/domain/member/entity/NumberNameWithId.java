package com.gapple.weeingback.domain.member.entity;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NumberNameWithId {
  private String info;

  private String id;
}
