package com.javafxvalidation.converters;

import com.javafxvalidation.utils.CoinUtils;
import javafx.util.StringConverter;
import org.bitcoinj.core.Coin;

public class StringCoinConverter extends StringConverter<Coin> {
    @Override
    public String toString(Coin coin) {
        return coin != null ? coin.toFriendlyString() : "";
    }

    @Override
    public Coin fromString(String value) {
        return CoinUtils.isValidCoin(value) ? Coin.parseCoin(value) : null;
    }
}
