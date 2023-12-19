package com.gapple.weeingback.domain.diary.repository;

import com.gapple.weeingback.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
