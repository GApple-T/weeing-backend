package com.gapple.weeingback.domain.consulting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToConsultingResponse {
    private String id;

    private Long issuedAt;

    private Long time;

    private String description;
}
