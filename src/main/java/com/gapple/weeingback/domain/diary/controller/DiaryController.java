package com.gapple.weeingback.domain.diary.controller;

import com.gapple.weeingback.domain.diary.entity.dto.request.DiarySubmitRequest;
import com.gapple.weeingback.domain.diary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @PostMapping
    public ResponseEntity submitDiary(@Valid @RequestBody DiarySubmitRequest request){
        return diaryService.submitDiary(request);
    }
}
