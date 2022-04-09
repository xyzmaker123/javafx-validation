package com.javafxvalidation.validators;

import com.javafxvalidation.core.Validator;
import com.javafxvalidation.utils.ValidationUtils;

public class DoubleValidator implements Validator {
    private static DoubleValidator instance;

    public static DoubleValidator getInstance() {
        if (instance == null) {
            instance = new DoubleValidator();
        }
        return instance;
    }

    private DoubleValidator() {}
    
    @Override
    public boolean validate(String text) {
        return ValidationUtils.isValidDouble(text);
    }

    @Override
    public String getMessage() {
        return "No valid number";
    }
}
