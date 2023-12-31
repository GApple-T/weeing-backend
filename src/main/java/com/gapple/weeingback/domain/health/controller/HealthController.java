package com.gapple.weeingback.domain.health.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/api/health")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("ok");
    }
}
