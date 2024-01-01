package com.gapple.weeingback.domain.diary.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryListRequest {
    private Long studentGrade;

    private Long studentClass;
}
