package com.api.hospital.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetPatientByCpfResponse {

    private String name;

    private String cpf;

    private String cep;

    private String street;

    private String district;

    private String city;

    private String state;

    private String symptoms;

    private String status;
}