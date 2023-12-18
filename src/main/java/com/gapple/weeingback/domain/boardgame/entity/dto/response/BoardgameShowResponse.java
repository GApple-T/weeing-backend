package com.gapple.weeingback.domain.boardgame.entity.dto.response;

import com.gapple.weeingback.domain.boardgame.entity.ToBoardgameDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardgameShowResponse {
    private String success;
    private List<ToBoardgameDto> boardgames;
}
