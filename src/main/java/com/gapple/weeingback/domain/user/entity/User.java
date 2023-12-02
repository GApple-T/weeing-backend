package com.gapple.weeingback.domain.user.entity;

import com.gapple.weeingback.domain.okay.entity.Okay;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.security.Principal;

@Table
@Entity
@Builder
@Getter @Setter
public class User {
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
  private Okay okay;

  public User(){}

  public User(Long id, String name, String email, String password, Okay okay) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.okay = okay;
  }
}
