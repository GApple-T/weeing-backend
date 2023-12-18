package com.gapple.weeingback.domain.boardgame.controller;

import com.gapple.weeingback.domain.boardgame.entity.Boardgame;
import com.gapple.weeingback.domain.boardgame.entity.dto.request.BoardgameCreateRequest;
import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameCreateResponse;
import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameShowResponse;
import com.gapple.weeingback.domain.boardgame.repository.BoardgameRepository;
import com.gapple.weeingback.domain.boardgame.service.BoardgameService;
import com.gapple.weeingback.domain.boardgame.service.implmentation.BoardgameServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
}
