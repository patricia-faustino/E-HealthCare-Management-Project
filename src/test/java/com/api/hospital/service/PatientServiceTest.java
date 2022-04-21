package com.api.hospital.service;

import com.api.hospital.helper.AddressHelper;
import com.api.hospital.helper.PatientHelper;
import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.SavePatientRequest;
import com.api.hospital.repository.AddressRepository;
import com.api.hospital.repository.PatientRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private AddressRepository addressRepository;

    private Patient patient;
    private Address address;
    private SavePatientRequest savePatientRequest;

    @Before
    public void before() {
        address = AddressHelper.buildAddress();
        patient = PatientHelper.buildPatient();
        savePatientRequest = PatientHelper.buildSavePatientRequest();
    }

}