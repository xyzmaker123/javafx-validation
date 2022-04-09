package com.javafxvalidationbisqdemo.views;

import com.javafxvalidationbisqdemo.views.offer.Offer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.bitcoinj.core.Coin;

public class CreateOfferModel {
    protected final ObjectProperty<Coin> amount = new SimpleObjectProperty<>();
    protected final StringProperty marketPricePercentage = new SimpleStringProperty("");
    protected final StringProperty volume = new SimpleStringProperty("");

    public CreateOfferModel() {
        amount.addListener((item, o, n) -> {
            System.out.println("Amount updated: " + (n != null ? n.toFriendlyString() : null));    
        });
    }
    
    public Offer createOffer() {
        Double marketPricePercentageAsDouble = Double.parseDouble(marketPricePercentage.get());
        Double volumeAsDouble = Double.parseDouble(volume.get());

        return new Offer(amount.get(), marketPricePercentageAsDouble, volumeAsDouble);
    }
}
