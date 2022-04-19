package com.api.hospital.service;

import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Doctor;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.SaveDoctorRequest;
import com.api.hospital.repository.AddressRepository;
import com.api.hospital.repository.DoctorRepository;
import com.api.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AddressRepository addressRepository;
    private final HospitalRepository hospitalRepository;

    public void saveDoctor(SaveDoctorRequest request) {
        Hospital hospital = this.findByCnpj(request.getHospitalCnpj());

        Address address = saveAddress(request);

        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setCrm(request.getCrm());
        doctor.setHospital(hospital);
        doctor.setSpecialty(request.getSpecialty());
        doctor.setAddress(address);
        doctorRepository.save(doctor);
    }

    private Address saveAddress(SaveDoctorRequest request) {
        Address address = new Address();
        address.setCep(request.getCep());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setStreet(request.getStreet());
        address.setState(request.getState());
        addressRepository.save(address);
        return address;
    }

    private Hospital findByCnpj(String cnpj) {
        Optional<Hospital> hospital = hospitalRepository.findByCnpj(cnpj);
        return hospital.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
    }
}