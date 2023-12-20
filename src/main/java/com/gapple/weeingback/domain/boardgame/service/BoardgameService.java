package com.gapple.weeingback.domain.boardgame.service;

import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameCreateResponse;
import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameShowResponse;
import org.springframework.http.ResponseEntity;

import java.util.function.LongFunction;

public interface BoardgameService {
    ResponseEntity<BoardgameCreateResponse> submitBoardgame(Long maxOf);

    ResponseEntity<BoardgameShowResponse> showAllBoardgame();

    ResponseEntity doneBoardgame(String id);
}
