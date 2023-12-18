package com.gapple.weeingback.domain.boardgame.repository;

import com.gapple.weeingback.domain.boardgame.entity.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {
    boolean existsById(UUID id);
}
