package com.gapple.weeingback.domain.member.repository;

import com.gapple.weeingback.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  boolean existsMemberByEmail(String email);
  Member findMemberByEmail(String email);
}