package com.gapple.weeingback.domain.okay.controller;

import com.gapple.weeingback.domain.okay.service.implementation.OkayServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gapple.weeingback.domain.okay.entity.dto.OkaySubmitRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/okay")
@RequiredArgsConstructor
public class OkayController {
    private final OkayServiceImpl okayService;

    @PostMapping("/submit")
    public void submitOkay(@Valid OkaySubmitRequest request){
        okayService.submitOkay(request);
    }
}
