package com.gapple.weeingback.domain.diary.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryListRequest {
    @NotNull
    private Long studentGrade;

    private Long studentClass;
}
