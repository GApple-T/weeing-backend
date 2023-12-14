package com.gapple.weeingback.domain.member.entity;

import com.gapple.weeingback.domain.consultation.entity.Consultation;

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
  @JoinColumn(name = "check_id")
  private Consultation consultation;

  public Member(){}

  public Member(Long id, String name, String email, String password, Consultation consultation) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.consultation = consultation;
  }
}
