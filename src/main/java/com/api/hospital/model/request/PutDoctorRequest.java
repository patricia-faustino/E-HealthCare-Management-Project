package com.api.hospital.model.request;

import com.api.hospital.validators.CrmNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PutDoctorRequest {

    @CrmNumberConstraint
    private String crm;

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
}
