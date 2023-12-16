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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsultingServiceImpl implements ConsultingService {
    private final MemberRepository memberRepository;
    private final ConsultingRepository consultingRepository;

    @Override
    @Transactional
    public ResponseEntity<ConsultingSubmitResponse> submitConsulting(ConsultingSubmitRequest request){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findMemberById(UUID.fromString(id));

        Consulting consulting = new Consulting(Instant.now().toEpochMilli(), request.getClassTime());
        consulting.setMember(member);

        member.addConsulting(consulting);

        memberRepository.save(member);
        consultingRepository.save(consulting);

        return ResponseEntity.accepted().body(new ConsultingSubmitResponse("ok"));
    }

    @Override
    @Transactional
    public ResponseEntity<ConsultingShowResponse> showConsulting() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        List<ToConsultingResponse> consultingResponses = new ArrayList<>();

        List<Consulting> consults = consultingRepository.findConsultingByMember_Id(UUID.fromString(id));
        consults.forEach(consulting -> {
            consultingResponses.add(new ToConsultingResponse(
                    consulting.getId().toString(),
                    consulting.getIssuedAt(),
                    consulting.getClassTime(),
                    consulting.getDescription()));
        });

        return ResponseEntity.ok().body(new ConsultingShowResponse(consultingResponses, "okay"));
    }

    @Override
    public ResponseEntity<ConsultingCancleResponse> cancleConsulting(ConsultingCancleRequest request) {
        consultingRepository.removeById(UUID.fromString(request.getConsultingId()));
        return ResponseEntity.ok().body(new ConsultingCancleResponse("ok"));
    }
}
