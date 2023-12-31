package com.gapple.weeingback.domain.member.repository;

import com.gapple.weeingback.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  boolean existsMemberByEmail(String email);
  Optional<Member> findMemberByEmail(String email);
  Optional<Member> findMemberById(UUID id);
}
