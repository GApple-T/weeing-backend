package com.gapple.weeingback.domain.boardgame.entity;

import com.gapple.weeingback.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    @Column(nullable = false)
    private UUID creator;

    @Column(nullable = false)
    private Long maxOf;

    @Column(nullable = false)
    private Long joined;

    @Column
    @OneToMany(fetch = FetchType.EAGER)
    private List<Member> members;

    public ToBoardgameDto toDto(Boardgame boardgame){
        return new ToBoardgameDto(
                boardgame.getMaxOf(),
                boardgame.getJoined(),
                boardgame.getCreator().toString(),
                boardgame.getMembers());
    }
}
