package com.javafxvalidation.validators;

import com.javafxvalidation.core.Validator;
import com.javafxvalidation.utils.ValidationUtils;

import java.text.MessageFormat;

public class MinLengthValidator implements Validator {
    public static MinLengthValidator getInstance(int minLength) {
        return new MinLengthValidator(minLength);
    }

    private final int minLength;
    
    private MinLengthValidator(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean validate(String text) {
        return ValidationUtils.hasMinLength(text, minLength);
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("Too short, min length is: {0}", minLength);
    }
}
