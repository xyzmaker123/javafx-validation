package com.javafxvalidation.utils;

import org.bitcoinj.core.Coin;

public class CoinUtils {
    public static boolean isValidCoin(String text) {
        try {
            Coin.parseCoin(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
