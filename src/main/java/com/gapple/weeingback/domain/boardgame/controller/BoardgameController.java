package com.gapple.weeingback.domain.boardgame.controller;

import com.gapple.weeingback.domain.boardgame.entity.dto.request.BoardgameCreateRequest;
import com.gapple.weeingback.domain.boardgame.entity.dto.request.BoardgameDoneRequest;
import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameCreateResponse;
import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameDoneResponse;
import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameShowResponse;
import com.gapple.weeingback.domain.boardgame.service.BoardgameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/boardgame")
public class BoardgameController {
    private final BoardgameService boardgameService;

    @PostMapping("/create")
    public ResponseEntity<BoardgameCreateResponse> createBoardgame(@Valid @RequestBody BoardgameCreateRequest boardgameCreateRequest){
        return boardgameService.createBoardgame(boardgameCreateRequest.getMaxOf());
    }

    @GetMapping("/show-all")
    public ResponseEntity<BoardgameShowResponse> showAllBoardgame(){
        return boardgameService.showAllBoardgame();
    }

    @DeleteMapping("/done")
    public ResponseEntity<BoardgameDoneResponse> doneBoardgame(@RequestBody BoardgameDoneRequest request){
        return boardgameService.doneBoardgame(request.getId());
    }
}
