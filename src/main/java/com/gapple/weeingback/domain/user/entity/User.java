package com.gapple.weeingback.domain.user.entity;

import com.gapple.weeingback.domain.check.entity.Check;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity(name = "USER_TABLE")
@Getter @Setter
public class User {
  @Id @GeneratedValue
  @Column(name = "USER_ID")
  private Long id;
  private String name;
  private String email;
  private String password;
  private UserRole role;
  @OneToOne
  @JoinTable(name = "CHECK_ID")
  private Check check;
}
