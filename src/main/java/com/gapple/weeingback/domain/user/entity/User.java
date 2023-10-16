package com.gapple.weeingback.domain.user.entity;

import com.gapple.weeingback.domain.check.entity.Check;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter @Setter
public class User {
  @Id @GeneratedValue
  private Long id;
  private String name;
  private String email;
  private String password;

  @OneToOne
  @JoinColumn(name = "check_id")
  private Check check;
//  private UserRole role;
}
