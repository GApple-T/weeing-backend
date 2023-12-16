package com.gapple.weeingback.domain.boardgame.entity;

import com.gapple.weeingback.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boardgame {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID id;

    @Column
    private UUID creator;

    @Column
    @OneToMany(fetch = FetchType.LAZY)
    private List<Member> members;
}
