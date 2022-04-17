package com.api.hospital.controller;

import com.api.hospital.model.request.SaveHospitalRequest;
import com.api.hospital.model.response.GetHospitalByNameResponse;
import com.api.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{name}")
    public ResponseEntity<GetHospitalByNameResponse> getHospitalByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findHospitalByName(name));
    }
}
