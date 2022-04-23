package com.api.hospital.controller;

import com.api.hospital.model.request.SaveConsultationRequest;
import com.api.hospital.model.response.GetConsultationResponse;
import com.api.hospital.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{crm}")
    public ResponseEntity<List<GetConsultationResponse>> getByCrm(@PathVariable String crm) {
        return ResponseEntity.ok(service.getByCrm(crm));
    }
}