package org.example.spring_demo_stockmanagement.pl.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageFormatValidator.class})
public @interface ImageFormat {

    String message() default "Invalid image format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}