package com.gapple.weeingback.domain.boardgame.service.implmentation;

import com.gapple.weeingback.domain.boardgame.domain.Boardgame;
import com.gapple.weeingback.domain.boardgame.domain.BoardgameMemberDto;
import com.gapple.weeingback.domain.boardgame.domain.ToBoardgameDto;
import com.gapple.weeingback.domain.boardgame.domain.dto.response.BoardgameShowResponse;
import com.gapple.weeingback.domain.boardgame.repository.BoardgameRepository;
import com.gapple.weeingback.domain.boardgame.service.BoardgameService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.exception.BoardgameNotFoundException;
import com.gapple.weeingback.global.exception.MemberNotFoundException;
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
        Member member = memberRepository.findMemberById(UUID.fromString(id)).orElseThrow(MemberNotFoundException::new);

        Boardgame boardgame = Boardgame.builder()
                .creator(member)
                .maxOf(maxOf)
                .build();

        boardgameRepository.save(boardgame);
    }


    @Override
    @Transactional
    public void joinBoardgame(UUID boardgameId) {
        UUID memberId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        Member member = memberRepository.findMemberById(memberId).orElseThrow(MemberNotFoundException::new);

        Boardgame boardgame = boardgameRepository.findBoardgameById(boardgameId).orElseThrow(BoardgameNotFoundException::new);
        boardgame.addMember(member);

        boardgameRepository.save(boardgame);
    }

    @Override
    @Transactional
    public void doneBoardgame(UUID boardgameId) {
        boardgameRepository.deleteById(boardgameId);
    }
    @Override
    public BoardgameShowResponse showAllBoardgame() {
        List<Boardgame> boardgames = boardgameRepository.findAll();
        List<ToBoardgameDto> boardgameDtos = new ArrayList<>();
        boardgames.forEach(boardgame ->
            boardgameDtos.add(toDto(boardgame))
        );

        return new BoardgameShowResponse(boardgameDtos);
    }

    public ToBoardgameDto toDto(Boardgame boardgame){
        List<BoardgameMemberDto> players = new ArrayList<>();

        boardgame.getMembers().forEach(member ->
                players.add(
                        Member.toBoardgameMemberDto(member)
                )
        );

        return new ToBoardgameDto(
                boardgame.getId().toString(),
                boardgame.getMaxOf(),
                Member.toBoardgameMemberDto(boardgame.getCreator()),
                players);
    }
}