package com.api.hospital.controller;

import com.api.hospital.model.request.PutHospitalRequest;
import com.api.hospital.model.request.SaveHospitalRequest;
import com.api.hospital.model.response.GetHospitalsByNameResponse;
import com.api.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SaveHospitalRequest request) {
        service.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<GetHospitalsByNameResponse>> getHospitalsByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findHospitalsByName(name));
    }

    @PutMapping
    public ResponseEntity<Void> changeNumberAvailableBeds(@RequestBody @Valid PutHospitalRequest request) {
        service.changeNumberAvailableBeds(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cnpj/{cnpj}")
    public ResponseEntity<Void> delete(@PathVariable String cnpj) {
        service.delete(cnpj);
        return ResponseEntity.ok().build();
    }
}