package com.gapple.weeingback.domain.user.repository;

import com.gapple.weeingback.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsUserByEmail(String email);
  User findUserByEmail(String email);
}
