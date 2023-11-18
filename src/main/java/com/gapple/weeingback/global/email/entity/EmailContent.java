package com.gapple.weeingback.global.email.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class EmailContent {
    private String to;
    private String subject;
    private String description;
}
