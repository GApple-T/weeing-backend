package com.gapple.weeingback.domain.okay.entity;

import com.gapple.weeingback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Table
@Entity
@Getter @Setter
public class Okay {
  @Id @GeneratedValue
  private Long id;
  private Long issuedAt; // 상담 신청 일자
  private Long startAt; // 언제 가야하는지
  private boolean isAccess = false; // 선생님이 승인했는지
  private boolean isTrue = false; // 활성화된 Check 인지

  @OneToOne(mappedBy = "okay")
  private User user;

  public Okay(boolean isTrue){
    this.isTrue = isTrue;
  }

  public Okay(){}
}
