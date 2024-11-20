package com.tutorial.tutorial.Product;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatePriceClass.class)
public @interface ValidatePrice {
    String message() default "Price should be more than 10";
    Class<?>[] groups() default{};
    Class<? extends Payload>[]payload()default{};
}
