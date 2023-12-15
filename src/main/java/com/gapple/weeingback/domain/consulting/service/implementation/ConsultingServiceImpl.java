package com.gapple.weeingback.domain.consulting.service.implementation;

import com.gapple.weeingback.domain.consulting.entity.Consulting;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import com.gapple.weeingback.domain.consulting.service.ConsultingService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ConsultingServiceImpl implements ConsultingService {
    private final MemberRepository memberRepository;

    @Transactional
    public void submitConsulting(ConsultingSubmitRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberByEmail(email);
        Consulting consulting = member.getConsulting();
        consulting.setIssuedAt(Instant.now().toEpochMilli());
        consulting.setStartAt(request.getStartAt());

        memberRepository.save(member);
    }
}
