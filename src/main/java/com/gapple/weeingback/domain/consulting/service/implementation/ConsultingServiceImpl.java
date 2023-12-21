package com.gapple.weeingback.domain.consulting.service.implementation;

import com.gapple.weeingback.domain.consulting.entity.Consulting;
import com.gapple.weeingback.domain.consulting.entity.ToConsultingResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingCancleRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingCancleResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingShowResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingSubmitResponse;
import com.gapple.weeingback.domain.consulting.repository.ConsultingRepository;
import com.gapple.weeingback.domain.consulting.service.ConsultingService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.exception.SameConsultingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultingServiceImpl implements ConsultingService {
    private final MemberRepository memberRepository;
    private final ConsultingRepository consultingRepository;

    @Override
    @Transactional
    public ResponseEntity<ConsultingSubmitResponse> submitConsulting(ConsultingSubmitRequest request){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberById(UUID.fromString(id));

        Consulting consulting = Consulting.toConsulting(
                Instant.now().toEpochMilli(),
                request.getClassTime(),
                request.getDescription());

        if(consultingRepository.existsByClassTime(consulting.getClassTime())){
            throw new SameConsultingException();
        }

        member.addConsulting(consulting);

        consultingRepository.save(consulting);
        memberRepository.save(member);

        return ResponseEntity.accepted().body(new ConsultingSubmitResponse("ok"));
    }

    @Override
    @Transactional
    public ResponseEntity<ConsultingShowResponse> showConsulting() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberById(UUID.fromString(id));

        List<ToConsultingResponse> consultingResponses = new ArrayList<>();

        List<Consulting> consults = member.getConsulting();
        consults.forEach(consulting -> consultingResponses.add(new ToConsultingResponse(
                consulting.getId().toString(),
                consulting.getIssuedAt(),
                consulting.getClassTime(),
                consulting.getDescription())));

        return ResponseEntity.ok().body(new ConsultingShowResponse(consultingResponses, "ok"));
    }

    @Override
    @Transactional
    public ResponseEntity<ConsultingCancleResponse> cancleConsulting(ConsultingCancleRequest request) {
        UUID id = UUID.fromString(request.getConsultingId());
        Consulting consulting = consultingRepository.findById(id);

        if(consulting != null){
            consultingRepository.deleteById(UUID.fromString(request.getConsultingId()));
        } else throw new IllegalArgumentException();

        return ResponseEntity.ok().body(new ConsultingCancleResponse("ok"));
    }
}
