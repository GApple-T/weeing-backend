package com.gapple.weeingback.domain.diary.entity.dto.response;

import com.gapple.weeingback.domain.diary.entity.Diary;
import com.gapple.weeingback.domain.diary.entity.DiaryAndMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.css.CSSMediaRule;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryMyListResponse {
    private List<Diary> diaries;
    private String name;
    private Long grade;
    private Long classroom;
    private Long number;
}
