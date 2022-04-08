package com.javafxvalidation.validators;

import com.javafxvalidation.core.Validator;
import com.javafxvalidation.utils.CoinUtils;

public class CoinValidator implements Validator {
    public static String CODE = "Coin";
    
    @Override
    public boolean validate(String text) {
        return CoinUtils.isValidCoin(text);
    }

    @Override
    public String getCode() {
        return CODE;
    }


}
