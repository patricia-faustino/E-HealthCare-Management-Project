package com.api.hospital.service;


import com.api.hospital.helper.AddressHelper;
import com.api.hospital.helper.HospitalHelper;
import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.SaveHospitalRequest;
import com.api.hospital.repository.AddressRepository;
import com.api.hospital.repository.HospitalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceTest {

    @InjectMocks
    private HospitalService hospitalService;

    @Mock
    private HospitalRepository hospitalRepository;

    @Mock
    private AddressRepository addressRepository;

    private SaveHospitalRequest saveHospitalRequest;
    private Address address;
    private Hospital hospital;

    @Before
    public void before() {
        saveHospitalRequest = HospitalHelper.buildSaveHospitalRequest();
        address = AddressHelper.buildAddress();
        hospital = HospitalHelper.buildHospital();
    }

    @Test
    public void saveHospital() {
        hospitalService.saveHospital(saveHospitalRequest);

        verify(addressRepository, times(1)).save(address);
        verify(hospitalRepository, times(1)).save(hospital);
    }
}
