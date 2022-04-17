package com.api.hospital.controller;

import com.api.hospital.model.request.SaveHospitalRequest;
import com.api.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService service;

    @PostMapping
    public ResponseEntity<Void> saveHospital(@RequestBody @Valid  SaveHospitalRequest request) {
        service.saveHospital(request);
        return ResponseEntity.ok().build();
    }
}
