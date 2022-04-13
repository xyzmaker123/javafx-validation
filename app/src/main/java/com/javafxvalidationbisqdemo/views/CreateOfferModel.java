package com.javafxvalidationbisqdemo.views;

import com.javafxvalidationbisqdemo.views.offer.Offer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.bitcoinj.core.Coin;

public class CreateOfferModel {
    protected final ObjectProperty<Coin> amount = new SimpleObjectProperty<>();
    protected final DoubleProperty marketPricePercentage = new SimpleDoubleProperty(0.0);
    protected final DoubleProperty volume = new SimpleDoubleProperty(0.0);

    private final double marketPrice = 42000;

    public CreateOfferModel() {
        amount.addListener((item, o, n) -> {
            System.out.println("Amount updated: " + (n != null ? n.toFriendlyString() : null));    
        });
        
        addBindings();
    }

    public Offer createOffer() {
        return new Offer(amount.get(), marketPricePercentage.get(), volume.get());
    }

    private void addBindings() {
        amount.addListener((e, o, n) -> volume.set(calculateVolume()));
        marketPricePercentage.addListener((e, o, n) -> volume.set(calculateVolume()));
        volume.addListener((e, o, n) -> amount.set(calculateAmount()));
        
        
        
//        volume.bind(
//                Bindings.createDoubleBinding(
//                        () -> calculateVolume(), 
//                        amount,
//                        marketPricePercentage
//                )
//        );
    }
    
    private Coin calculateAmount() {
        return volume.get() == 0.0
                ? Coin.ZERO 
                : Coin.valueOf((long) (volume.get() * 100000000 / (1 + marketPricePercentage.get() / 100) / marketPrice));
    }
    
    private double calculateVolume() {
        return amount.get() == null 
                ? 0.0 
                : amount.get().getValue() / 100000000 * (1 + marketPricePercentage.get() / 100) * marketPrice;
    }
}
