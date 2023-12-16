package com.gapple.weeingback.domain.consulting.service;

import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitResponse;
import org.springframework.http.ResponseEntity;

public interface ConsultingService {
    ResponseEntity<ConsultingSubmitResponse> submitConsulting(ConsultingSubmitRequest request);
}
