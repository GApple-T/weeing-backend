package com.gapple.weeingback.domain.consulting.service;

import com.gapple.weeingback.domain.consulting.entity.ToConsultingResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingCancleRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingUpdateRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingShowResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConsultingService {
    void submitConsulting(ConsultingSubmitRequest request);

    List<ToConsultingResponse> showConsulting();

    void cancleConsulting(ConsultingCancleRequest request);

    ConsultingShowResponse showMyConsulting();

    void updateConsulting(ConsultingUpdateRequest request);
}
