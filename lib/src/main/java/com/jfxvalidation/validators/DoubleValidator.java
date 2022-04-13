package com.jfxvalidation.validators;

import com.jfxvalidation.core.Validator;
import com.jfxvalidation.utils.ValidationUtils;

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
