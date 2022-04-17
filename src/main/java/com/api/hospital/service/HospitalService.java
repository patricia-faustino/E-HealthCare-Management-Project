package com.api.hospital.service;

import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.PutHospitalRequest;
import com.api.hospital.model.request.SaveHospitalRequest;
import com.api.hospital.model.response.GetHospitalByNameResponse;
import com.api.hospital.repository.AddressRepository;
import com.api.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    private final AddressRepository addressRepository;

    public void saveHospital(SaveHospitalRequest request) {
        Address address = this.saveAddress(request);

        Hospital hospital = new Hospital();
        hospital.setAddress(address);
        hospital.setName(request.getName());
        hospital.setAvailableBeds(request.getAvailableBeds());
        hospital.setTotalBeds(request.getTotalBeds());
        hospital.setCnpj(request.getCnpj());
        hospitalRepository.save(hospital);
    }

    public GetHospitalByNameResponse findHospitalByName(String name) {
        Hospital hospital= this.findByName(name);
        return GetHospitalByNameResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .availableBeds(hospital.getAvailableBeds())
                .cnpj(hospital.getCnpj())
                .totalBeds(hospital.getTotalBeds())
                .cep(hospital.getAddress().getCep())
                .city(hospital.getAddress().getCity())
                .district(hospital.getAddress().getDistrict())
                .street(hospital.getAddress().getStreet())
                .state(hospital.getAddress().getState())
                .build();
    }

    public void changeNumberAvailableBeds(PutHospitalRequest request) {
        Hospital hospital = this.findByCnpj(request.getCnpj());
        hospital.setAvailableBeds(hospital.getAvailableBeds() - request.getBedsOccupation());
        hospitalRepository.save(hospital);
    }

    private Address saveAddress(SaveHospitalRequest request) {
        Address address = new Address();
        address.setCep(request.getCep());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setStreet(request.getStreet());
        address.setState(request.getState());
        addressRepository.save(address);
        return address;
    }

    //todo: melhorar quando a entidade não for encontrada
    private Hospital findByName(String name) {
        Optional<Hospital> hospital = hospitalRepository.findByName(name);
        return hospital.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
    }

    private Hospital findByCnpj(String cnpj) {
        Optional<Hospital> hospital = hospitalRepository.findByCnpj(cnpj);
        return hospital.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
    }
}
