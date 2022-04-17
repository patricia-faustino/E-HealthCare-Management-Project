package com.api.hospital.service;

import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.SaveHospitalRequest;
import com.api.hospital.repository.AddressRepository;
import com.api.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        hospital.setCpnj(request.getCpnj());
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
}
