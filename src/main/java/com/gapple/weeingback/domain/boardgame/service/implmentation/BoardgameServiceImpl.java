package com.gapple.weeingback.domain.boardgame.service.implmentation;

import com.gapple.weeingback.domain.boardgame.domain.Boardgame;
import com.gapple.weeingback.domain.boardgame.domain.ToBoardgameDto;
import com.gapple.weeingback.domain.boardgame.domain.dto.response.BoardgameShowResponse;
import com.gapple.weeingback.domain.boardgame.repository.BoardgameRepository;
import com.gapple.weeingback.domain.boardgame.service.BoardgameService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.exception.BoardgameExistsException;
import com.gapple.weeingback.global.exception.SameCreatorException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardgameServiceImpl implements BoardgameService {
    private final BoardgameRepository boardgameRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void submitBoardgame(Long maxOf) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberById(UUID.fromString(id));

        if(member.getBoardgame() != null){
            throw new BoardgameExistsException();
        }

        Boardgame boardgame = Boardgame.builder()
                .creator(member)
                .maxOf(maxOf)
                .joined(0L)
                .build();

        member.setBoardgame(boardgame);

        memberRepository.save(member);
        boardgameRepository.save(boardgame);
    }

    @Override
    public BoardgameShowResponse showAllBoardgame() {
        List<Boardgame> boardgames = boardgameRepository.findAll();
        List<ToBoardgameDto> boardgameDtos = new ArrayList<>();
        boardgames.forEach(boardgame ->
            boardgameDtos.add(boardgame.toDto(boardgame))
        );

        return new BoardgameShowResponse(boardgameDtos);
    }

    @Override
    public void joinBoardgame(UUID id) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberById(UUID.fromString(memberId));

        Boardgame boardgame = boardgameRepository.findBoardgameById(id);

        if(boardgame.getCreator().getId().toString().equals(memberId)){
            throw new SameCreatorException();
        }

        boardgame.addMember(member);

        boardgameRepository.save(boardgame);
    }

    @Override
    public void doneBoardgame(UUID id) {
        Boardgame boardgame =
                boardgameRepository.findBoardgameById(id);

        boardgameRepository.delete(boardgame);
    }
}
