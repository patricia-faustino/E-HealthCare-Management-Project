package com.api.hospital.service;

import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.PutHospitalRequest;
import com.api.hospital.model.request.SaveAddressRequest;
import com.api.hospital.model.request.SaveHospitalRequest;
import com.api.hospital.model.response.GetHospitalsByNameResponse;
import com.api.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final AddressService addressService;

    public void save(SaveHospitalRequest request) {
        SaveAddressRequest saveAddressRequest = SaveAddressRequest.builder()
                .cep(request.getCep())
                .city(request.getCity())
                .district(request.getDistrict())
                .street(request.getStreet())
                .state(request.getState())
                .build();
        Address address = addressService.save(saveAddressRequest);

        Hospital hospital = new Hospital();
        hospital.setAddress(address);
        hospital.setName(request.getName());
        hospital.setAvailableBeds(request.getAvailableBeds());
        hospital.setTotalBeds(request.getTotalBeds());
        hospital.setCnpj(request.getCnpj());
        hospitalRepository.save(hospital);
    }

    public List<GetHospitalsByNameResponse> findHospitalsByName(String name) {
        List<Hospital> hospitals = this.findByName(name);
        List<GetHospitalsByNameResponse> response = new ArrayList<>();

        if(hospitals != null) {
            for (Hospital hospital : hospitals) {
                response.add(GetHospitalsByNameResponse.builder()
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
                        .build());
            }
        }
        return response;
    }

    public void changeNumberAvailableBeds(PutHospitalRequest request) {
        Hospital hospital = this.findByCnpj(request.getCnpj());
        hospital.setAvailableBeds(hospital.getAvailableBeds() - request.getBedsOccupation());
        hospitalRepository.save(hospital);
    }

    public Hospital findByCnpj(String cnpj) {
        Optional<Hospital> hospital = hospitalRepository.findByCnpj(cnpj);
        return hospital.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
    }

    public void delete(String cpnj) {
        hospitalRepository.delete(this.findByCnpj(cpnj));
    }

    //todo: melhorar excecao quando a entidade não for encontrada
    private List<Hospital> findByName(String name) {
        return hospitalRepository.findByNameContainingIgnoreCase(name);
    }
}
