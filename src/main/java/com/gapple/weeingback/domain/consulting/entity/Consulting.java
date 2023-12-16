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
  private Long issuedAt;

  @Column(nullable = false)
  private int classTime;

  @Column(nullable = false)
  private boolean isAccess;

  public Consulting(Long issuedAt, int classTime){
    this.issuedAt = issuedAt;
    this.classTime = classTime;
  }
}
