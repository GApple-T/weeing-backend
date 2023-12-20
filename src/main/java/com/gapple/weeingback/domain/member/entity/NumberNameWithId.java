package com.gapple.weeingback.domain.member.entity;

import com.gapple.weeingback.domain.boardgame.entity.Boardgame;
import com.gapple.weeingback.domain.consulting.entity.Consulting;
import com.gapple.weeingback.domain.diary.entity.Diary;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NumberNameWithId {
  private String info;

  private String id;
}
