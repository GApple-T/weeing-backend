package com.gapple.weeingback.domain.schedule.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCreateRequest {
    private long classTime;
}
