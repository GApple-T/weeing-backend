package com.gapple.weeingback.domain.consulting.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Table
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulting {
  @Id
  @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name="uuid2", strategy = "uuid2")
  private UUID id;

  @Column(nullable = false)
  private Long issuedAt;

  @Column(nullable = false)
  private Long time;

  @Column(nullable = false)
  private boolean isAccess;

  @Column(columnDefinition = "VARCHAR(3000)")
  private String description;

  public Consulting(Long issuedAt, Long time, String description) {
    this.issuedAt = issuedAt;
    this.time = time;
    this.description = description;
  }

  public static Consulting toConsulting(Long issuedAt, Long time, String description){
    return new Consulting(issuedAt, time, description);
  }
}
