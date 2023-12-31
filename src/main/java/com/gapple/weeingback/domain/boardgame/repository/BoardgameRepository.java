package com.gapple.weeingback.domain.boardgame.repository;

import com.gapple.weeingback.domain.boardgame.domain.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {

    Optional<Boardgame> findBoardgameById(UUID id);

    void deleteById(UUID id);
}
