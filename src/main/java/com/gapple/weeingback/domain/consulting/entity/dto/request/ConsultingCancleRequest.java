package com.gapple.weeingback.domain.consulting.entity.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConsultingCancleRequest {
    @NotBlank
    private String consultingId;
}
