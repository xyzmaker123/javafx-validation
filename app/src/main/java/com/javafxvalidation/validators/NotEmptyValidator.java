package com.javafxvalidation.validators;

import com.javafxvalidation.core.Validator;

public class NotEmptyValidator implements Validator {

    @Override
    public boolean validate(String text) {
        return text != null && !text.isEmpty();
    }

    @Override
    public String getCode() {
        return "NotEmpty";
    }


}
