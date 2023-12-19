package com.gapple.weeingback.domain.diary.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiarySubmitRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Long studentGrade;

    @NotNull
    private Long studentClass;
}
