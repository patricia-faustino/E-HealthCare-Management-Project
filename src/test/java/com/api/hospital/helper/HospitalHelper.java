package com.api.hospital.helper;

import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.SaveHospitalRequest;

public class HospitalHelper {

    public static SaveHospitalRequest buildSaveHospitalRequest() {
        return SaveHospitalRequest.builder()
                .name("Hospital Test")
                .cpnj("85.086.201/0001-06")
                .totalBeds(100L)
                .availableBeds(100L)
                .cep("54753-782")
                .city("Manaus")
                .state("Amazonas")
                .district("Alvorada")
                .street("Rua C 11")
                .build();
    }

    public static Hospital buildHospital() {
        return Hospital.builder()
                .id(1L)
                .name("Hospital Test")
                .cpnj("85.086.201/0001-06")
                .totalBeds(100L)
                .availableBeds(100L)
                .address(AddressHelper.buildAddress())
                .build();
    }
}