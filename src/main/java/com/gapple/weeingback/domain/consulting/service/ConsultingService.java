package com.gapple.weeingback.domain.consulting.service;

import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingCancleRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingCancleResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingShowResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingSubmitResponse;
import org.springframework.http.ResponseEntity;

public interface ConsultingService {
    ResponseEntity<ConsultingSubmitResponse> submitConsulting(ConsultingSubmitRequest request);

    ResponseEntity<ConsultingShowResponse> showConsulting();

    ResponseEntity<ConsultingCancleResponse> cancleConsulting(ConsultingCancleRequest request);

    ResponseEntity<ConsultingShowResponse> showMyConsulting();
}
