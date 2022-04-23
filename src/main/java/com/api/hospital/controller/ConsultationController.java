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

    @GetMapping("/getByCrm/{crm}")
    public ResponseEntity<List<GetConsultationResponse>> getByCrm(@PathVariable String crm) {
        return ResponseEntity.ok(service.getByCrm(crm));
    }

    @GetMapping("/getByCpf/{cpf}")
    public ResponseEntity<List<GetConsultationResponse>> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.getByCpf(cpf));
    }

    @GetMapping("/getByCnpj/{cnpj}")
    public ResponseEntity<List<GetConsultationResponse>> getByCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(service.getByCnpj(cnpj));
    }
}