package com.gapple.weeingback.domain.consulting.entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulting {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long issuedAt; // 상담 신청 일자

  @Column(nullable = false)
  private Long startAt; // 언제 가야하는지

  @Column(nullable = false)
  private boolean isAccess; // 선생님이 승인했는지

  public Consulting(Long issuedAt, Long startAt){
    this.issuedAt = issuedAt;
    this.startAt = startAt;
  }
}
