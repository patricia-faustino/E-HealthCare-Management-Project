package com.api.hospital.controller;

import com.api.hospital.model.request.SaveConsultationRequest;
import com.api.hospital.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/consultation")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SaveConsultationRequest request) {
        service.save(request);
        return ResponseEntity.ok().build();
    }
}