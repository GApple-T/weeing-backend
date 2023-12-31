package com.gapple.weeingback.domain.auth.repository;

import com.gapple.weeingback.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findRefreshTokenByKey(UUID key);
    boolean existsByKey(UUID key);
}
