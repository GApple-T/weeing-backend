package com.gapple.weeingback.domain.boardgame.service.implmentation;

import com.gapple.weeingback.domain.boardgame.entity.Boardgame;
import com.gapple.weeingback.domain.boardgame.entity.ToBoardgameDto;
import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameCreateResponse;
import com.gapple.weeingback.domain.boardgame.entity.dto.response.BoardgameShowResponse;
import com.gapple.weeingback.domain.boardgame.repository.BoardgameRepository;
import com.gapple.weeingback.domain.boardgame.service.BoardgameService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardgameServiceImpl implements BoardgameService {
    private final BoardgameRepository boardgameRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ResponseEntity<BoardgameCreateResponse> createBoardgame(Long maxOf) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberById(UUID.fromString(id));

        log.info(member.getEmail());

        Boardgame boardgame = Boardgame.builder()
                .creator(member.getId())
                .maxOf(maxOf)
                .joined(0L)
                .build();

        if(member.getBoardgame() == null){
            boardgameRepository.save(boardgame);
        } else throw new IllegalArgumentException();

        return ResponseEntity.ok().body(new BoardgameCreateResponse("ok"));
    }

    @Override
    public ResponseEntity<BoardgameShowResponse> showAllBoardgame() {
        List<Boardgame> boardgames = boardgameRepository.findAll();
        List<ToBoardgameDto> boardgameDtos = new ArrayList<>();
        boardgames.forEach(boardgame ->
            boardgameDtos.add(boardgame.toDto(boardgame))
        );

        return ResponseEntity.ok().body(new BoardgameShowResponse("ok", boardgameDtos));
    }
}
