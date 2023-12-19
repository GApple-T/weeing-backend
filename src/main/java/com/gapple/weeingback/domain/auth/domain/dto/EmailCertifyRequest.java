package com.gapple.weeingback.domain.auth.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailCertifyRequest {
    @NotBlank
    private String email;

    private String success;
}
