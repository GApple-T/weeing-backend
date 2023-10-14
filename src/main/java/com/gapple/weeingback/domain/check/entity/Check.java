package com.gapple.weeingback.domain.check.entity;

import com.gapple.weeingback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity(name = "CHECK_TABLE")
@Getter @Setter
public class Check {
  @Id @GeneratedValue
  @Column(name = "CHECK_ID")
  private Long id;
  private Long issuedAt; // 상담 신청 일자
  private Long start; // 언제 가야하는지
  private boolean isTrue; // 선생님이 승인했는지r
}
