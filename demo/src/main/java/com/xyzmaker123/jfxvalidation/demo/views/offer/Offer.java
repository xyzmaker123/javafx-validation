package com.xyzmaker123.jfxvalidation.demo.views.offer;

import org.bitcoinj.core.Coin;

public class Offer {
    private final Coin amount;
    private final Double marketPriceMargin;
    private final Double volume;

    public Offer(Coin amount, Double marketPriceMargin, Double volume) {
        this.amount = amount;
        this.marketPriceMargin = marketPriceMargin;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "amount=" + amount +
                ", marketPriceMargin=" + marketPriceMargin +
                ", volume=" + volume +
                '}';
    }
}
