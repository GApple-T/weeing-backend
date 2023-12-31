package com.gapple.weeingback.domain.schedule.repository;

import com.gapple.weeingback.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
