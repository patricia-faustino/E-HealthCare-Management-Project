package com.api.hospital.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GetConsultationResponse {

    private String doctorName;

    private String crm;

    private String patientName;

    private String patientCpf;

    private String hospitalName;

    private String hospitalCnpj;

    private String symptoms;
}
