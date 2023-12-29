package com.gapple.weeingback.domain.boardgame.controller;

import com.gapple.weeingback.domain.boardgame.domain.dto.request.BoardgameJoinRequest;
import com.gapple.weeingback.domain.boardgame.domain.dto.request.BoardgameSubmitRequest;
import com.gapple.weeingback.domain.boardgame.domain.dto.request.BoardgameDoneRequest;
import com.gapple.weeingback.domain.boardgame.domain.dto.response.BoardgameShowResponse;
import com.gapple.weeingback.domain.boardgame.service.BoardgameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> submitBoardgame(@Valid @RequestBody BoardgameSubmitRequest request){
        boardgameService.submitBoardgame(request.getMaxOf());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Void> joinBoardgame(@Valid @RequestBody BoardgameJoinRequest request){
        boardgameService.joinBoardgame(UUID.fromString(request.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<BoardgameShowResponse> showAllBoardgame(){
        BoardgameShowResponse response = boardgameService.showAllBoardgame();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/done")
    public ResponseEntity<Void> doneBoardgame(@RequestBody BoardgameDoneRequest request){
        boardgameService.doneBoardgame(UUID.fromString(request.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
