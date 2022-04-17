package com.api.hospital.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetHospitalByNameResponse {

    private Long id;

    private String name;

    private String cnpj;

    private Long totalBeds;

    private Long availableBeds;

    private String cep;

    private String street;

    private String district;

    private String city;

    private String state;
}
