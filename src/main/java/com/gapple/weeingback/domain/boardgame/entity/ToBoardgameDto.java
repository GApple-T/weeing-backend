package com.gapple.weeingback.domain.boardgame.entity;

import com.gapple.weeingback.domain.member.entity.NumberNameWithId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToBoardgameDto {
    private String id;
    private Long maxOf;
    private Long joined;
    private NumberNameWithId creator;
    private List<NumberNameWithId> players;
}
