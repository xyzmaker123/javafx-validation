package com.javafxvalidationbisqdemo.views;

import com.javafxvalidation.controls.TextFieldWrapper;
import com.javafxvalidation.converters.StringCoinConverter;
import com.javafxvalidation.converters.StringNumberConverter;
import com.javafxvalidation.utils.BindingUtils;
import com.javafxvalidation.validators.CoinValidator;
import com.javafxvalidation.validators.DoubleValidator;
import com.javafxvalidation.validators.NotEmptyValidator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CreateOfferView1 extends GridPane {
    private final CreateOfferModel model;
    private TextFieldWrapper amountBox, marketPricePercentageBox, volumeBox;
    private Button saveButton;
    
    public CreateOfferView1() {
        super();
        model = new CreateOfferModel();
        initialize();
        addBindings();
    }

    void initialize() {
        amountBox = new TextFieldWrapper(
                "Amount of BTC to buy", 
                NotEmptyValidator.getInstance(),
                CoinValidator.getInstance()
        );
        
        marketPricePercentageBox = new TextFieldWrapper(
                "Below % from market price",
                NotEmptyValidator.getInstance(),
                DoubleValidator.getInstance()
        );

        volumeBox = new TextFieldWrapper(
                "Amount in USD to spend",
                NotEmptyValidator.getInstance(), 
                DoubleValidator.getInstance()
        );


        saveButton = new Button("Next step");
        saveButton.setOnAction(e -> {
            System.out.println(model.createOffer());
        });
        
        add(amountBox, 0, 0);
        add(marketPricePercentageBox, 1, 0);
        add(volumeBox, 2, 0);
        
        add(saveButton, 0, 1);

        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));
    }

    private void addBindings() {
        amountBox.textProperty().bindBidirectional(model.amount, new StringCoinConverter());
        marketPricePercentageBox.textProperty().bindBidirectional(model.marketPricePercentage, new StringNumberConverter());
        volumeBox.textProperty().bindBidirectional(model.volume, new StringNumberConverter());

        saveButton.disableProperty().bind(
                BindingUtils.createAnyNotNullBinding(
                        amountBox.errorProperty(),
                        marketPricePercentageBox.errorProperty(),
                        volumeBox.errorProperty()
                )
        );
    }
}
