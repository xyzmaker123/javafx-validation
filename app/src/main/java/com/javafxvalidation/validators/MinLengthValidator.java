package com.javafxvalidation.validators;

import com.javafxvalidation.core.Validator;

public class MinLengthValidator implements Validator {
    public static String CODE = "MinLength";
    
    private int minLength;
    
    public MinLengthValidator(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean validate(String text) {
        return text != null && text.length() >= minLength;
    }

    @Override
    public String getCode() {
        return CODE;
    }


}
