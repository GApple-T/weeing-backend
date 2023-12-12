package com.gapple.weeingback.domain.consultation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gapple.weeingback.domain.consultation.entity.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Consultation findOkayByMemberEmail(String email);
}
