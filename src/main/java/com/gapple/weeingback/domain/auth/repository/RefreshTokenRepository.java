package com.gapple.weeingback.domain.auth.repository;

import com.gapple.weeingback.domain.auth.dto.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findRefreshTokenById(UUID id);
    RefreshToken findRefreshTokenByToken(String token);
}
