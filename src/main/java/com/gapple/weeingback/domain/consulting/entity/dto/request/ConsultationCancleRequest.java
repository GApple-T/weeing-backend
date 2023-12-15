package com.gapple.weeingback.domain.consulting.entity.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConsultationCancleRequest {
    @Email
    private String email;
}
