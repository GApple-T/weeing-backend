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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
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
                .grade(member.getGrade())
                .classroom(member.getClassroom())
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
            diaries = diaryRepository.findAllByGrade(request.getStudentGrade());
        } else {
            diaries = diaryRepository.findAllByGradeAndClassroom(
                    request.getStudentGrade(),
                    request.getStudentClass()
            );
        }

        return new DiaryListResponse(diaries);
    }

    @Override
    public DiaryMyListResponse myListDiary() {
        UUID id = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        Member member = memberRepository.findMemberById(id).orElseThrow(MemberNotFoundException::new);
        List<Diary> diaries = member.getDiaries();

        log.info(diaries.toString());

        DiaryMyListResponse response = new DiaryMyListResponse(
                diaries,
                member.getName(),
                member.getGrade(),
                member.getClassroom(),
                member.getNumber());

        return response;
    }
}
