package com.api.hospital.service;

import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Doctor;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.PutDoctorRequest;
import com.api.hospital.model.request.SaveAddressRequest;
import com.api.hospital.model.request.SaveDoctorRequest;
import com.api.hospital.model.response.GetDoctorByCrmResponse;
import com.api.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AddressService addressService;
    private final HospitalService hospitalService;

    public void save(SaveDoctorRequest request) {
        Hospital hospital = hospitalService.findByCnpj(request.getHospitalCnpj());

        SaveAddressRequest saveAddressRequest = SaveAddressRequest.builder()
                .cep(request.getCep())
                .city(request.getCity())
                .district(request.getDistrict())
                .street(request.getStreet())
                .state(request.getState())
                .build();
        Address address = addressService.save(saveAddressRequest);

        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setCrm(request.getCrm());
        doctor.setHospital(hospital);
        doctor.setSpecialty(request.getSpecialty());
        doctor.setAddress(address);
        doctorRepository.save(doctor);
    }

    public GetDoctorByCrmResponse getByCrm(String crm) {
        Doctor doctor = findDoctorByCrm(crm);
        return GetDoctorByCrmResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .crm(doctor.getCrm())
                .specialty(doctor.getSpecialty().name())
                .hospitalCnpj(doctor.getHospital().getCnpj())
                .build();
    }

    public void delete(String crm) {
        doctorRepository.delete(this.findDoctorByCrm(crm));
    }

    public void putAddressDoctor(PutDoctorRequest request) {
        Doctor doctor = findDoctorByCrm(request.getCrm());
        doctor.getAddress().setStreet(request.getStreet());
        doctor.getAddress().setDistrict(request.getDistrict());
        doctor.getAddress().setCity(request.getCity());
        doctor.getAddress().setCep(request.getCep());
        doctor.getAddress().setState(request.getState());
        doctorRepository.save(doctor);
    }

    private Doctor findDoctorByCrm(String crm) {
        Optional<Doctor> doctor = doctorRepository.findByCrm(crm);
        doctor.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
        return doctor.get();
    }
}