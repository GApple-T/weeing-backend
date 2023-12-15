package com.gapple.weeingback.domain.consultation.controller;

import com.gapple.weeingback.domain.consultation.entity.dto.request.ConsultationCancleRequestCancleRequest;
import com.gapple.weeingback.domain.consultation.service.implementation.ConsultationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gapple.weeingback.domain.consultation.entity.dto.request.ConsultationCancleRequestSubmitRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/okay")
@RequiredArgsConstructor
public class ConsultationController {
    private final ConsultationServiceImpl service;

    @PostMapping("/submit")
    public ResponseEntity<Void> submitOkay(@Valid ConsultationCancleRequestSubmitRequest request){
        service.submitOkay(request);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
