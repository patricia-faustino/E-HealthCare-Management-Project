package com.api.hospital.model.request;

import com.api.hospital.model.enums.SPECIALTY;
import com.api.hospital.validators.CrmNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SaveDoctorRequest {

    @NotBlank
    private String name;

    @CrmNumberConstraint
    private String crm;
    
    @NotNull
    private SPECIALTY specialty;

    @NotBlank
    private String cep;

    @NotBlank
    private String street;

    @NotBlank
    private String district;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String hospitalCnpj;
}