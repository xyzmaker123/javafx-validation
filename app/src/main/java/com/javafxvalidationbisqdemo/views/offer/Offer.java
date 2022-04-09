package com.javafxvalidationbisqdemo.views.offer;

import org.bitcoinj.core.Coin;

public class Offer {
    private Coin amount;
    private Double marketPriceMargin;
    private Double volume;

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
