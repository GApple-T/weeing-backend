package com.gapple.weeingback.domain.member.entity;

import com.gapple.weeingback.domain.boardgame.domain.Boardgame;
import com.gapple.weeingback.domain.boardgame.domain.BoardgameMemberDto;
import com.gapple.weeingback.domain.consulting.entity.Consulting;

import com.gapple.weeingback.domain.diary.entity.Diary;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

@Table
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member implements GrantedAuthority {
  @Id
  @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name="uuid2", strategy = "uuid2")
  private UUID id;

  @Column(columnDefinition = "VARCHAR(50)", nullable = false)
  private String email;

  @Column(columnDefinition = "VARCHAR(20)")
  private String name;

  @Column(columnDefinition = "INTEGER")
  private Long grade;

  @Column(columnDefinition = "INTEGER")
  private Long classroom;

  @Column(columnDefinition = "INTEGER")
  private Long number;

  @Column(columnDefinition = "VARCHAR(80)", nullable = false)
  private String password;

  @Column(columnDefinition = "VARCHAR(80)", nullable = false)
  private String role;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "consulting_id")
  private List<Consulting> consulting;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "diary_id")
  private List<Diary> diaries;

  public void addConsulting(Consulting consulting){
    this.consulting.add(consulting);
  }

  public void addDiary(Diary diary){
    this.diaries.add(diary);
  }


  @Override
  public String getAuthority() {
    return role;
  }

  public static BoardgameMemberDto toBoardgameMemberDto(Member member){
    return new BoardgameMemberDto(
            member.getGrade(),
            member.getClassroom(),
            member.getNumber(),
            member.getName(),
            member.getId().toString()
    );
  }
}
