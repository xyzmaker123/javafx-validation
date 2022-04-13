package com.jfxvalidation.converters;

import javafx.util.StringConverter;
import org.bitcoinj.core.Coin;
import com.jfxvalidation.utils.ValidationUtils;

public class StringCoinConverter extends StringConverter<Coin> {
    @Override
    public String toString(Coin coin) {
        return coin != null ? coin.toFriendlyString().replace("BTC", "").trim() : "";
    }

    @Override
    public Coin fromString(String value) {
        return ValidationUtils.isValidCoin(value) ? Coin.parseCoin(value) : null;
    }
}
