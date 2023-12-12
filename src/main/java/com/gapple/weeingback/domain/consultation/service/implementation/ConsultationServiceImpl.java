package com.gapple.weeingback.domain.consultation.service.implementation;

import com.gapple.weeingback.domain.consultation.entity.Consultation;
import com.gapple.weeingback.domain.consultation.entity.dto.request.ConsultationCancleRequestSubmitRequest;
import com.gapple.weeingback.domain.consultation.repository.ConsultationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl {
    private final ConsultationRepository okayRepository;

    @Transactional
    public void submitOkay(ConsultationCancleRequestSubmitRequest request){
        // TODO 상담 확인서 저장하기
        Consultation consultation = okayRepository.findOkayByMemberEmail(request.getEmail());
        consultation.setIssuedAt(request.getIssuedAt());
        consultation.setStartAt(request.getStartAt());
        consultation.setTrue(true);

        okayRepository.save(consultation);
    }
}
