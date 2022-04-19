package com.api.hospital.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CrmNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CrmNumberConstraint {
    String message() default "Invalid crm number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}