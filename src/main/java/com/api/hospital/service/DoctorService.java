package com.api.hospital.service;

import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Doctor;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.PutDoctorRequest;
import com.api.hospital.model.request.SaveDoctorRequest;
import com.api.hospital.model.response.GetByCrmResponse;
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

    public GetByCrmResponse getByCrm(String crm) {
        Doctor doctor = findDoctorByCrm(crm);

        return GetByCrmResponse.builder()
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

    private Doctor findDoctorByCrm(String crm) {
        Optional<Doctor> doctor = doctorRepository.findByCrm(crm);
        doctor.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
        return doctor.get();
    }
}