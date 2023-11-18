package com.gapple.weeingback.domain.okay.entity.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OkayCancleRequest {
    @Email
    private String email;
}
