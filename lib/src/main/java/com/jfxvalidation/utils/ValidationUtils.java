package com.jfxvalidation.utils;

import org.bitcoinj.core.Coin;

public class ValidationUtils {
    public static boolean isValidCoin(String text) {
        try {
            Coin.parseCoin(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isValidDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean hasMinLength(String text, int minLength) {
        return text != null && text.length() >= minLength;
    }
    
    public static boolean isNotEmpty(String text) {
        return hasMinLength(text, 1);
    }
}
