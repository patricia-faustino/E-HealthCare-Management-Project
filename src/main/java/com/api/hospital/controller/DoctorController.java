package com.api.hospital.controller;

import com.api.hospital.model.request.PutDoctorRequest;
import com.api.hospital.model.request.PutHospitalRequest;
import com.api.hospital.model.request.SaveDoctorRequest;
import com.api.hospital.model.response.GetByCrmResponse;
import com.api.hospital.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @PostMapping
    public ResponseEntity<Void> saveDoctor(@RequestBody @Valid SaveDoctorRequest request) {
        service.saveDoctor(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{crm}")
    public ResponseEntity<GetByCrmResponse> getByCrm(@PathVariable String crm) {
        return ResponseEntity.ok(service.getByCrm(crm));
    }

    @DeleteMapping("/{crm}")
    public ResponseEntity<Void> delete(@PathVariable String crm) {
        service.delete(crm);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> putAddressDoctor(@RequestBody @Valid PutDoctorRequest request) {
        service.putAddressDoctor(request);
        return ResponseEntity.ok().build();
    }
}