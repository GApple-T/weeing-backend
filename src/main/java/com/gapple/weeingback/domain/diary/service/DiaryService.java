package com.gapple.weeingback.domain.diary.service;

import com.gapple.weeingback.domain.diary.entity.dto.request.DiarySubmitRequest;
import org.springframework.http.ResponseEntity;

public interface DiaryService {
    ResponseEntity submitDiary(DiarySubmitRequest request);
}
