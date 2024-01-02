package com.gapple.weeingback.domain.boardgame.domain;

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
public class Boardgame {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member creator;

    @Column(nullable = false)
    private Long maxOf;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Member> members;

    public void addMember(Member member){
        members.add(member);
    }
}
