package com.api.hospital.service;

import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.PutPatientRequest;
import com.api.hospital.model.request.SaveAddressRequest;
import com.api.hospital.model.request.SavePatientRequest;
import com.api.hospital.model.response.GetPatientByCpfResponse;
import com.api.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        patient.setAddress(addressService.save(saveAddressRequest));
        patientRepository.save(patient);
    }

    public void putName(PutPatientRequest request) {
        Patient patient = this.findByCpf(request.getCpf());
        patient.setName(request.getName());
        patientRepository.save(patient);
    }

    public void delete(String cpf) {
        Patient patient = this.findByCpf(cpf);
        patientIsInactive(patient);
        patient.setStatus(Boolean.FALSE);
        patientRepository.save(patient);
    }

    public GetPatientByCpfResponse getByCpf(String cpf) {
        Patient patient = this.findByCpf(cpf);
        return GetPatientByCpfResponse.builder()
                .name(patient.getName())
                .cpf(patient.getCpf())
                .cep(patient.getAddress().getCep())
                .street(patient.getAddress().getStreet())
                .district(patient.getAddress().getDistrict())
                .city(patient.getAddress().getCity())
                .state(patient.getAddress().getState())
                .status(patient.getStatus().toString())
                .build();
    }

    private void patientIsInactive(Patient patient) {
        if(patient.getStatus() == Boolean.FALSE) {
            throw new UnsupportedOperationException("Patient is already inactive!");
        }
    }

    public Patient findByCpf(String cpf) {
        Optional<Patient> patient = patientRepository.findByCpf(cpf);
        return patient.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
    }
}