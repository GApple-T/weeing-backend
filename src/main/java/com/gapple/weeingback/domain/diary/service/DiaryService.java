package com.gapple.weeingback.domain.diary.service;

import com.gapple.weeingback.domain.diary.entity.dto.request.DiaryListRequest;
import com.gapple.weeingback.domain.diary.entity.dto.request.DiarySubmitRequest;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryListResponse;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryMyListResponse;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiarySubmitResponse;
import org.springframework.http.ResponseEntity;

public interface DiaryService {
    ResponseEntity<DiarySubmitResponse> submitDiary(DiarySubmitRequest request);
    ResponseEntity<DiaryListResponse> listDiary(DiaryListRequest request);
    ResponseEntity<DiaryMyListResponse> myListDiary();
}
