package com.api.hospital.model.request;

import com.api.hospital.validators.CrmNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SaveConsultationRequest {

    @CPF
    @NotNull
    private String cpf;

    @CrmNumberConstraint
    private String crm;

    @CNPJ
    private String cnpj;

    @NotBlank
    private String symptoms;
}