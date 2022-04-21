package com.api.hospital.service;

import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.SavePatientRequest;
import com.api.hospital.repository.AddressRepository;
import com.api.hospital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;

    public void save(SavePatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setCpf(request.getCpf());
        patient.setSymptoms(request.getSymptoms());
        patient.setAddress(this.saveAddress(request));
        patientRepository.save(patient);
    }

    private Address saveAddress(SavePatientRequest request) {
        Address address = new Address();
        address.setCep(request.getCep());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setStreet(request.getStreet());
        address.setState(request.getState());
        addressRepository.save(address);
        return address;
    }
}