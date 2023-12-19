package com.gapple.weeingback.domain.diary.service.implementation;

import com.gapple.weeingback.domain.diary.entity.Diary;
import com.gapple.weeingback.domain.diary.entity.dto.request.DiarySubmitRequest;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiarySubmitResponse;
import com.gapple.weeingback.domain.diary.repository.DiaryRepository;
import com.gapple.weeingback.domain.diary.service.DiaryService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    @Override
    public ResponseEntity<DiarySubmitResponse> submitDiary(DiarySubmitRequest request) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberById(UUID.fromString(id));

        Diary diary = Diary.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .accessRange(request.getAccessRange())
                .build();

        member.addDiary(diary);

        diaryRepository.save(diary);
        memberRepository.save(member);

        return ResponseEntity.ok().body(new DiarySubmitResponse("ok"));
    }
}
