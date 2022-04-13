package com.jfxvalidation.validators;

import com.jfxvalidation.core.Validator;
import com.jfxvalidation.utils.ValidationUtils;

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
