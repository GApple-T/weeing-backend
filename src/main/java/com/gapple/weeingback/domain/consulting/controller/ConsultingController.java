package com.gapple.weeingback.domain.consulting.controller;

import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingCancleRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingCancleResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingShowResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingSubmitResponse;
import com.gapple.weeingback.domain.consulting.service.ConsultingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/api/consulting")
@RequiredArgsConstructor
public class ConsultingController {
    private final ConsultingService consultingService;

    @PostMapping("/submit")
    public ResponseEntity<ConsultingSubmitResponse> submitConsulting(@Valid @RequestBody ConsultingSubmitRequest request){
        return consultingService.submitConsulting(request);
    }

    @GetMapping("/list")
    public ResponseEntity<ConsultingShowResponse> showConsulting(){
        return consultingService.showConsulting();
    }

    @DeleteMapping("/cancle")
    public ResponseEntity<ConsultingCancleResponse> cancleConsulting(@Valid @RequestBody ConsultingCancleRequest request){
        return consultingService.cancleConsulting(request);
    }

    @GetMapping("/my-list")
    public ResponseEntity<ConsultingShowResponse> showMyConsulting(){
        return consultingService.showMyConsulting();
    }
}
