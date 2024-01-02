package com.gapple.weeingback.domain.boardgame.domain;

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
    private BoardgameMemberDto creator;
    private List<BoardgameMemberDto> players;
}
