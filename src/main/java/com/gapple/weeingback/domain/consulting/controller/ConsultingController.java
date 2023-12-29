package com.gapple.weeingback.domain.consulting.controller;

import com.gapple.weeingback.domain.consulting.entity.ToConsultingResponse;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingCancleRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingUpdateRequest;
import com.gapple.weeingback.domain.consulting.entity.dto.response.ConsultingShowResponse;
import com.gapple.weeingback.domain.consulting.service.ConsultingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.gapple.weeingback.domain.consulting.entity.dto.request.ConsultingSubmitRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/consulting")
@RequiredArgsConstructor
public class ConsultingController {
    private final ConsultingService consultingService;

    @PostMapping("/submit")
    public ResponseEntity<Void> submitConsulting(@Validated @RequestBody ConsultingSubmitRequest request){
        consultingService.submitConsulting(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ConsultingShowResponse> showConsulting(){
        List<ToConsultingResponse> consultings = consultingService.showConsulting();
        return new ResponseEntity<>(new ConsultingShowResponse(consultings) ,HttpStatus.OK);
    }

    @DeleteMapping("/cancle")
    public ResponseEntity<Void> cancleConsulting(@Validated @RequestBody ConsultingCancleRequest request){
        consultingService.cancleConsulting(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my-list")
    public ResponseEntity<ConsultingShowResponse> showMyConsulting(){
        ConsultingShowResponse response = consultingService.showMyConsulting();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateConsulting(@Validated @RequestBody ConsultingUpdateRequest request){
        consultingService.updateConsulting(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
