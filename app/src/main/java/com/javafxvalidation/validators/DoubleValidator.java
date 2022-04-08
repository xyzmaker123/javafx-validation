package com.javafxvalidation.validators;

import com.javafxvalidation.core.Validator;

public class DoubleValidator implements Validator {
    public static String CODE = "Double";
    
    @Override
    public boolean validate(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getCode() {
        return CODE;
    }


}
