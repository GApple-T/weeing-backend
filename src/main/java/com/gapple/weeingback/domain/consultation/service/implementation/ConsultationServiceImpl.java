package com.gapple.weeingback.domain.consultation.service.implementation;

import com.gapple.weeingback.domain.consultation.entity.Consultation;
import com.gapple.weeingback.domain.consultation.entity.dto.request.ConsultationCancleRequestSubmitRequest;

import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl {
    private final MemberRepository memberRepository;

    @Transactional
    public void submitOkay(ConsultationCancleRequestSubmitRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberByEmail(email);
        Consultation consultation = member.getConsultation();
        consultation.setIssuedAt(Instant.now().toEpochMilli());
        consultation.setStartAt(request.getStartAt());

        memberRepository.save(member);
    }
}
