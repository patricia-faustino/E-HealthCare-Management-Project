package com.api.hospital.controller;

import com.api.hospital.model.request.SaveDoctorRequest;
import com.api.hospital.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @PostMapping
    public ResponseEntity<Void> saveHospital(@RequestBody @Valid SaveDoctorRequest request) {
        service.saveDoctor(request);
        return ResponseEntity.ok().build();
    }
}