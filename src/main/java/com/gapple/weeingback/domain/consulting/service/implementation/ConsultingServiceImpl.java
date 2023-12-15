package com.gapple.weeingback.domain.consulting.service.implementation;

import com.gapple.weeingback.domain.consulting.entity.Consulting;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitResponse;
import com.gapple.weeingback.domain.consulting.service.ConsultingService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultingServiceImpl implements ConsultingService {
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity<ConsultingSubmitResponse> submitConsulting(ConsultingSubmitRequest request){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberById(UUID.fromString(id));

        if(member.getConsulting() != null){
            member.setConsulting(new Consulting(Instant.now().toEpochMilli(), request.getClassTime()));
        }

        memberRepository.save(member);

        return ResponseEntity.accepted().body(new ConsultingSubmitResponse("okay"));
    }
}
