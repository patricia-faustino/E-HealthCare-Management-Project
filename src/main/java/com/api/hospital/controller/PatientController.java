package com.api.hospital.controller;

import com.api.hospital.model.request.PutPatientRequest;
import com.api.hospital.model.request.SavePatientRequest;
import com.api.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SavePatientRequest request) {
        service.save(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> putName(@RequestBody PutPatientRequest request) {
        service.putName(request);
        return ResponseEntity.ok().build();
    }
}