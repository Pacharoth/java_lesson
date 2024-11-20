package com.tutorial.tutorial.Product;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatePriceClass implements ConstraintValidator<ValidatePrice, Double> {
    @Override
    public void initialize(ValidatePrice constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value > 10;
    }

}
