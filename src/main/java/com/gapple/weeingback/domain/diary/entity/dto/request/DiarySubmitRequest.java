package com.gapple.weeingback.domain.diary.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiarySubmitRequest {
    private Long accessRange;

    private String title;

    private String description;
}
