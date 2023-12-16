package com.gapple.weeingback.domain.consulting.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultingSubmitRequest {
    @NotNull
    private int classTime;

    @NotBlank
    private String description;
}
