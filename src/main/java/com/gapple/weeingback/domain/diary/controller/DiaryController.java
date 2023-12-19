package com.gapple.weeingback.domain.diary.controller;

import com.gapple.weeingback.domain.diary.entity.dto.request.DiaryListRequest;
import com.gapple.weeingback.domain.diary.entity.dto.request.DiarySubmitRequest;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryListResponse;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiarySubmitResponse;
import com.gapple.weeingback.domain.diary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @PostMapping("/submit")
    public ResponseEntity<DiarySubmitResponse> submitDiary(@Valid @RequestBody DiarySubmitRequest request){
        return diaryService.submitDiary(request);
    }

    @GetMapping("/list")
    public ResponseEntity<DiaryListResponse> listDiary(@Valid @RequestBody DiaryListRequest request){
        return diaryService.listDiary(request);
    }

    @GetMapping("/my-list")
    public ResponseEntity myListDiary(){
        return diaryService.myListDiary();
    }
}
