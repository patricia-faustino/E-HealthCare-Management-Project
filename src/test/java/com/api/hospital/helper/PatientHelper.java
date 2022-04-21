package com.api.hospital.helper;

import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.PutPatientRequest;

public class PatientHelper {

    public static Patient buildPatientActive() {
        return Patient.builder()
                .id(1L)
                .name("Patient Test")
                .cpf("123.456.789-00")
                .symptoms("Headache, fever")
                .address(AddressHelper.buildAddress())
                .status(Boolean.TRUE)
                .build();
    }

    public static Patient buildPatientInactive() {
        return Patient.builder()
                .id(1L)
                .name("Patient Test")
                .cpf("123.456.789-00")
                .symptoms("Headache, fever")
                .address(AddressHelper.buildAddress())
                .status(Boolean.FALSE)
                .build();
    }

    public static PutPatientRequest buildPutPatientRequest() {
        return PutPatientRequest.builder()
                .name("Social Name Test")
                .cpf("123.456.789-00")
                .build();
    }
}