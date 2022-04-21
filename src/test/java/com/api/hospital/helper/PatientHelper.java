package com.api.hospital.helper;

import com.api.hospital.model.entities.Patient;
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

    public static SavePatientRequest buildSavePatientRequest() {
        return SavePatientRequest.builder()
                .name("Patient Test")
                .cpf("123.456.789-00")
                .symptoms("Headache, fever")
                .cep("54753-782")
                .city("Manaus")
                .state("Amazonas")
                .district("Alvorada")
                .street("Rua C 11")
                .build();
    }
}