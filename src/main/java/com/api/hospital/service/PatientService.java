package com.api.hospital.service;

import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.SaveAddressRequest;
import com.api.hospital.model.request.SavePatientRequest;
import com.api.hospital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final AddressService addressService;

    public void save(SavePatientRequest request) {
        SaveAddressRequest saveAddressRequest = SaveAddressRequest.builder()
                .cep(request.getCep())
                .city(request.getCity())
                .district(request.getDistrict())
                .street(request.getStreet())
                .state(request.getState())
                .build();
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setCpf(request.getCpf());
        patient.setSymptoms(request.getSymptoms());
        patient.setAddress(addressService.save(saveAddressRequest));
        patientRepository.save(patient);
    }
}