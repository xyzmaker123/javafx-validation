package com.xyzmaker123.jfxvalidation.validators;

import com.xyzmaker123.jfxvalidation.core.Validator;
import com.xyzmaker123.jfxvalidation.utils.ValidationUtils;

public class NotEmptyValidator implements Validator {
    private static NotEmptyValidator instance;

    public static NotEmptyValidator getInstance() {
        if (instance == null) {
            instance = new NotEmptyValidator();
        }
        return instance;
    }

    private NotEmptyValidator() {}
    
    @Override
    public boolean validate(String text) {
        return ValidationUtils.isNotEmpty(text);
    }

    @Override
    public String getMessage() {
        return "Cannot be empty";
    }
}
