package com.gapple.weeingback.domain.consulting.service;

import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultationSubmitRequest;

public interface ConsultingService {
    void submitConsulting(ConsultationSubmitRequest request);
}
