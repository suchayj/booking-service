package com.bookingservice.bookingservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContainerSizeConstraintValidator implements ConstraintValidator<ContainerSize, Integer> {

    public boolean isValid(Integer s, ConstraintValidatorContext cvc) {
        boolean result = s.equals(20) || s.equals(40);
        return result;
    }
}
