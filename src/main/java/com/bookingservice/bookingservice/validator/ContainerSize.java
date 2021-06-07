package com.bookingservice.bookingservice.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ContainerSizeConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainerSize {
    // error message
    public String message()

    default "Must Contain 20 or 40";

    // represents group of constraints
    public Class<?>[] groups() default {};

    // represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
