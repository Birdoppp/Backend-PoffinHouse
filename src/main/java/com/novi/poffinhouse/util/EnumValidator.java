package com.novi.poffinhouse.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {

    private String[] validValues;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();
        validValues = Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .toArray(String[]::new);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Invalid value. Valid values are: " + String.join(", ", validValues))
                    .addConstraintViolation();
            return false;
        }

        boolean isValid = Arrays.stream(validValues)
                .anyMatch(validValue -> validValue.equalsIgnoreCase(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Invalid value. Valid values are: " + String.join(", ", validValues))
                    .addConstraintViolation();
        }

        return isValid;
    }
}
