package com.gapple.weeingback.domain.consulting.controller;

import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitResponse;
import com.gapple.weeingback.domain.consulting.service.implementation.ConsultingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consulting")
@RequiredArgsConstructor
public class ConsultingController {
    private final ConsultingServiceImpl service;

    @PostMapping("/submit")
    public ResponseEntity<ConsultingSubmitResponse> submitOkay(@Valid @RequestBody ConsultingSubmitRequest request){
        return service.submitConsulting(request);
    }
}
