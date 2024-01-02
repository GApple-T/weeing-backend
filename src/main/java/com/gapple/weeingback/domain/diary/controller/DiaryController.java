package com.gapple.weeingback.domain.diary.controller;

import com.gapple.weeingback.domain.diary.entity.dto.request.DiaryListRequest;
import com.gapple.weeingback.domain.diary.entity.dto.request.DiarySubmitRequest;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryListResponse;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryMyListResponse;
import com.gapple.weeingback.domain.diary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @PostMapping("/submit")
    public ResponseEntity<Void> submitDiary(@Valid @RequestBody DiarySubmitRequest request){
        diaryService.submitDiary(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<DiaryListResponse> listDiary(@RequestParam Long grade, @RequestParam Long classroom){
        DiaryListResponse response = diaryService.listDiary(grade, classroom);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/my-list")
    public ResponseEntity<DiaryMyListResponse> myListDiary(){
        DiaryMyListResponse response = diaryService.myListDiary();
        return ResponseEntity.ok().body(response);
    }
}
