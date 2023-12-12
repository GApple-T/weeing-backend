package com.gapple.weeingback.domain.consultation.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConsultationCancleRequestSubmitRequest {
    @NotBlank
    private Long issuedAt; // 상담 신청 일자

    @NotBlank
    private Long startAt; // 언제 가야하는지

    @NotBlank
    private String email;
}
