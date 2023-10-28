package com.gapple.weeingback.domain.okay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gapple.weeingback.domain.okay.entity.Okay;

import java.util.Optional;

public interface OkayRepository extends JpaRepository<Okay, Long> {
    Okay findOkayByUserEmail(String email);
}
