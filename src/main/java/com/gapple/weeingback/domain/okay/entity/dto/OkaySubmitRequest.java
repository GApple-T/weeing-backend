package com.gapple.weeingback.domain.okay.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OkaySubmitRequest {
    @NotBlank
    private Long issuedAt; // 상담 신청 일자

    @NotBlank
    private Long startAt; // 언제 가야하는지

    @NotBlank
    private String email;
}
