package com.api.hospital.helper;

import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.PutPatientRequest;
import com.api.hospital.model.request.SavePatientRequest;

public class PatientHelper {

    public static Patient buildPatient() {
        return Patient.builder()
                .id(1L)
                .name("Patient Test")
                .cpf("123.456.789-00")
                .symptoms("Headache, fever")
                .address(AddressHelper.buildAddress())
                .build();
    }

    public static PutPatientRequest buildPutPatientRequest() {
        return PutPatientRequest.builder()
                .name("Social Name Test")
                .cpf("123.456.789-00")
                .build();
    }
}