package com.api.hospital.helper;

import com.api.hospital.model.entities.Doctor;
import com.api.hospital.model.enums.SPECIALTY;
import com.api.hospital.model.request.SaveDoctorRequest;

public class DoctorHelper {
    public static Doctor buildDoctor() {
        return Doctor.builder()
                .id(1L)
                .name("Doctor Test")
                .hospital(HospitalHelper.buildHospital())
                .crm("12345678900")
                .specialty(SPECIALTY.GENERAL_PRACTITIONER)
                .address(AddressHelper.buildAddress())
                .build();
    }

    public static SaveDoctorRequest buildSaveDoctorRequest () {
        return SaveDoctorRequest.builder()
                .name("Doctor Test")
                .crm("12345678900")
                .specialty(SPECIALTY.GENERAL_PRACTITIONER)
                .hospitalCnpj("85086201000106")
                .cep("54753-782")
                .city("Manaus")
                .state("Amazonas")
                .district("Alvorada")
                .street("Rua C 11")
                .build();
    }
}