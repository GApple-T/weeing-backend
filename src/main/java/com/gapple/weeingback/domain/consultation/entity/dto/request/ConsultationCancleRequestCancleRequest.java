package com.gapple.weeingback.domain.consultation.entity.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConsultationCancleRequestCancleRequest {
    @Email
    private String email;
}
