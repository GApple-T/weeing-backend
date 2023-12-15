package com.gapple.weeingback.domain.member.entity;

import com.gapple.weeingback.domain.consulting.entity.Consulting;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Builder
@Getter @Setter
public class Member {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "VARCHAR(15)")
  private String name;

  @Column(columnDefinition = "VARCHAR(50)", nullable = false)
  private String email;

  @Column(columnDefinition = "VARCHAR(80)", nullable = false)
  private String password;

  @OneToOne
  @JoinColumn(name = "consultation_id")
  private Consulting consulting;

  public Member(){}

  public Member(Long id, String name, String email, String password, Consulting consulting) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.consulting = consulting;
  }
}
