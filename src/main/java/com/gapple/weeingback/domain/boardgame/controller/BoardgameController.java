package com.gapple.weeingback.domain.boardgame.controller;

import com.gapple.weeingback.domain.boardgame.entity.dto.request.BoardgameJoinRequest;
import com.gapple.weeingback.domain.boardgame.entity.dto.request.BoardgameSubmitRequest;
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

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/boardgame")
public class BoardgameController {
    private final BoardgameService boardgameService;

    @PostMapping("/submit")
    public ResponseEntity<BoardgameCreateResponse> submitBoardgame(@Valid @RequestBody BoardgameSubmitRequest request){
        return boardgameService.submitBoardgame(request.getMaxOf());
    }

    @PostMapping("/join")
    public ResponseEntity joinBoardgame(@Valid @RequestBody BoardgameJoinRequest request){
        return boardgameService.joinBoardgame(UUID.fromString(request.getId()));
    }

    @GetMapping("/list")
    public ResponseEntity<BoardgameShowResponse> showAllBoardgame(){
        return boardgameService.showAllBoardgame();
    }

    @DeleteMapping("/done")
    public ResponseEntity<BoardgameDoneResponse> doneBoardgame(@RequestBody BoardgameDoneRequest request){
        return boardgameService.doneBoardgame(UUID.fromString(request.getId()));
    }
}
