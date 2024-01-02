package com.gapple.weeingback.domain.diary.service;

import com.gapple.weeingback.domain.diary.entity.dto.request.DiaryListRequest;
import com.gapple.weeingback.domain.diary.entity.dto.request.DiarySubmitRequest;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryListResponse;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryMyListResponse;
import org.springframework.http.ResponseEntity;

public interface DiaryService {
    void submitDiary(DiarySubmitRequest request);
    DiaryListResponse listDiary(Long grade, Long classroom);
    DiaryMyListResponse myListDiary();
}
