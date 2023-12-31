package com.gapple.weeingback.domain.boardgame.service;

import com.gapple.weeingback.domain.boardgame.domain.dto.response.BoardgameShowResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface BoardgameService {
    void submitBoardgame(Long maxOf);

    BoardgameShowResponse showAllBoardgame();

    void doneBoardgame(UUID id);

    void joinBoardgame(UUID id);
}
