package com.api.hospital.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CrmNumberValidator implements ConstraintValidator<CrmNumberConstraint, String> {

    @Override
    public void initialize(CrmNumberConstraint crmNumberConstraint) {
    }

    @Override
    public boolean isValid(String crmField,
                           ConstraintValidatorContext cxt) {
        return crmField != null && crmField.matches("^[0-9]{4,10}+$");
    }
}