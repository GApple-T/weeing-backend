package com.gapple.weeingback.domain.diary.service.implementation;

import com.gapple.weeingback.domain.diary.entity.Diary;
import com.gapple.weeingback.domain.diary.entity.dto.request.DiaryListRequest;
import com.gapple.weeingback.domain.diary.entity.dto.request.DiarySubmitRequest;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryListResponse;
import com.gapple.weeingback.domain.diary.entity.dto.response.DiaryMyListResponse;
import com.gapple.weeingback.domain.diary.repository.DiaryRepository;
import com.gapple.weeingback.domain.diary.service.DiaryService;
import com.gapple.weeingback.domain.member.entity.Member;
import com.gapple.weeingback.domain.member.repository.MemberRepository;
import com.gapple.weeingback.global.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public void submitDiary(DiarySubmitRequest request) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findMemberById(UUID.fromString(id)).orElseThrow(MemberNotFoundException::new);

        Diary diary = Diary.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .studentClass(request.getStudentClass())
                .studentGrade(request.getStudentGrade())
                .build();

        member.addDiary(diary);

        memberRepository.save(member);
        diaryRepository.save(diary);
    }

    @Override
    @Transactional(readOnly = true)
    public DiaryListResponse listDiary(DiaryListRequest request) {
        List<Diary> diaries;

        if(request.getStudentClass() == null){
            diaries = diaryRepository.findAllByStudentGrade(request.getStudentGrade());
        } else {
            diaries = diaryRepository.findAllByStudentGradeAndStudentClass(
                    request.getStudentGrade(),
                    request.getStudentClass()
            );
        }

        return new DiaryListResponse(diaries);
    }

    @Override
    public DiaryMyListResponse myListDiary() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findMemberById(UUID.fromString(id)).orElseThrow(MemberNotFoundException::new);
        List<Diary> diaries = member.getDiaries();

        return new DiaryMyListResponse(diaries);
    }
}
