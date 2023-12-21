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
  private Long classTime;

  @Column(nullable = false)
  private boolean isAccess;

  @Column(columnDefinition = "VARCHAR(3000)")
  private String description;

  public Consulting(Long issuedAt, Long classTime, String description) {
    this.issuedAt = issuedAt;
    this.classTime = classTime;
    this.description = description;
  }

  public static Consulting toConsulting(Long issuedAt, Long classTime, String description){
    return new Consulting(issuedAt, classTime, description);
  }
}
