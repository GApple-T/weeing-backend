package com.gapple.weeingback.domain.consulting.entity;

import com.gapple.weeingback.domain.member.entity.Member;
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
  private int classTime;

  @Column(nullable = false)
  private boolean isAccess;

  @Column(columnDefinition = "VARCHAR(3000)")
  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "MEMBER_ID")
  private Member member;


  public Consulting(Long issuedAt, int classTime){
    this.issuedAt = issuedAt;
    this.classTime = classTime;
  }
}
