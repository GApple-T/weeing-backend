package com.gapple.weeingback.domain.okay.controller;

import com.gapple.weeingback.domain.okay.entity.dto.request.OkayCancleRequest;
import com.gapple.weeingback.domain.okay.service.implementation.OkayServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gapple.weeingback.domain.okay.entity.dto.request.OkaySubmitRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/okay")
@RequiredArgsConstructor
public class OkayController {
    private final OkayServiceImpl service;

    @PostMapping("/submit")
    public ResponseEntity<Void> submitOkay(@Valid OkaySubmitRequest request){
        service.submitOkay(request);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // TODO /cancle
    @GetMapping("/cancle")
    public ResponseEntity<Void> cancleOkay(@Valid OkayCancleRequest request){
        return null;
    }

    // TODO /update
}
