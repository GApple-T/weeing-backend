package com.gapple.weeingback.domain.boardgame.domain.dto.response;

import com.gapple.weeingback.domain.boardgame.domain.ToBoardgameDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardgameShowResponse {
    private List<ToBoardgameDto> boardgames;
}
