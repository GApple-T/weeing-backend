package com.gapple.weeingback.domain.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryAndMember {
    private Diary diary;
    private Long grade;
    private Long classroom;
    private Long number;
    private String name;
}
