package com.gapple.weeingback.domain.diary.entity.dto.response;

import com.gapple.weeingback.domain.diary.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryListResponse {
    private List<Diary> diaries;

    private String success;
}
