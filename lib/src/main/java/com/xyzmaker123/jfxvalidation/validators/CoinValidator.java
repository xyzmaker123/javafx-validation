package com.xyzmaker123.jfxvalidation.validators;

import com.xyzmaker123.jfxvalidation.core.Validator;
import com.xyzmaker123.jfxvalidation.utils.ValidationUtils;

public class CoinValidator implements Validator {
    private static CoinValidator instance;
    
    public static CoinValidator getInstance() {
        if (instance == null) {
            instance = new CoinValidator();
        }
        return instance;
    }
    
    private CoinValidator() {}
    
    @Override
    public boolean validate(String text) {
        return ValidationUtils.isValidCoin(text);
    }
    
    @Override
    public String getMessage() {
        return "No valid coin";
    }
}
