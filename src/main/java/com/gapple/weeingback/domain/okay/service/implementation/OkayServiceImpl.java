package com.gapple.weeingback.domain.okay.service.implementation;

import com.gapple.weeingback.domain.okay.entity.Okay;
import com.gapple.weeingback.domain.okay.entity.dto.request.OkaySubmitRequest;
import com.gapple.weeingback.domain.okay.repository.OkayRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OkayServiceImpl {
    private final OkayRepository okayRepository;

    @Transactional
    public void submitOkay(OkaySubmitRequest request){
        // TODO 상담 확인서 저장하기
        Okay okay = okayRepository.findOkayByUserEmail(request.getEmail());
        okay.setIssuedAt(request.getIssuedAt());
        okay.setStartAt(request.getStartAt());
        okay.setTrue(true);

        okayRepository.save(okay);
    }
}
