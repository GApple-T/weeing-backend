package com.gapple.weeingback.domain.schedule.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @PostMapping("create-schedule")
    public ResponseEntity<Void> createSchedule(){
        return null;
    }
}
