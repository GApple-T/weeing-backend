package com.gapple.weeingback.domain.boardgame.service.implmentation;

import com.gapple.weeingback.domain.boardgame.domain.Boardgame;
import com.gapple.weeingback.domain.boardgame.domain.ToBoardgameDto;
import com.gapple.weeingback.domain.boardgame.domain.dto.response.BoardgameShowResponse;
import com.gapple.weeingback.domain.boardgame.repository.BoardgameRepository;
import com.gapple.weeingback.domain.boardgame.service.BoardgameService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.entity.NumberNameWithId;
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
    public BoardgameShowResponse showAllBoardgame() {
        List<Boardgame> boardgames = boardgameRepository.findAll();
        List<ToBoardgameDto> boardgameDtos = new ArrayList<>();
        boardgames.forEach(boardgame ->
            boardgameDtos.add(toDto(boardgame))
        );



        return new BoardgameShowResponse(boardgameDtos);
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

    public ToBoardgameDto toDto(Boardgame boardgame){
        List<NumberNameWithId> players = new ArrayList<>();
        List<Member> members = boardgame.getMembers();
        Member creator = boardgame.getCreator();

        members.forEach(m -> {
            players.add(new NumberNameWithId(
                    m.getGrade(),
                    m.getClassroom(),
                    m.getNumber(),
                    m.getName(),
                    m.getId().toString()
            ));
        });

        boardgame.getMembers().forEach(member ->
                players.add(new NumberNameWithId(
                        member.getGrade(),
                        member.getClassroom(),
                        member.getNumber(),
                        member.getName(),
                        member.getId().toString()
                ))
        );

        return new ToBoardgameDto(
                boardgame.getId().toString(),
                boardgame.getMaxOf(),
                new NumberNameWithId(
                        creator.getGrade(),
                        creator.getClassroom(),
                        creator.getNumber(),
                        creator.getName(),
                        creator.getId().toString()
                ),
                players);
    }
}


