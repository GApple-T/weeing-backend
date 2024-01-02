package com.gapple.weeingback.domain.boardgame.domain;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardgameMemberDto {
  private Long grade;

  private Long classroom;

  private Long number;

  private String name;

  private String id;
}
