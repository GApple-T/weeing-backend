package com.gapple.weeingback.domain.okay.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OkaySubmitRequest {
    @NotNull
    private Long issuedAt; // 상담 신청 일자
    @NotNull
    private Long startAt; // 언제 가야하는지
    @NotNull
    private String email;
}
