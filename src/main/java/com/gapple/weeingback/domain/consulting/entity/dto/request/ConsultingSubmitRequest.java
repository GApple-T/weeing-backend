package com.gapple.weeingback.domain.consulting.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConsultingSubmitRequest {
    @NotBlank
    private Long startAt;
}
