package com.api.hospital.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetDoctorByCrmResponse {

    private Long id;

    private String name;

    private String crm;

    private String specialty;

    private String hospitalCnpj;
}