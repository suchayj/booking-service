package com.bookingservice.bookingservice.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bookingservice.bookingservice.model.ContainerTypeEnum;

public class CustomerTypeSubSetValidator implements ConstraintValidator<CustomerTypeSubset, ContainerTypeEnum> {
    private ContainerTypeEnum[] subset;

    @Override
    public void initialize(CustomerTypeSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(ContainerTypeEnum value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
