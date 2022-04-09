package com.javafxvalidationbisqdemo.views;

import com.javafxvalidation.controls.TextFieldWrapper;
import com.javafxvalidation.converters.StringCoinConverter;
import com.javafxvalidation.utils.BindingUtils;
import com.javafxvalidation.validators.CoinValidator;
import com.javafxvalidation.validators.DoubleValidator;
import com.javafxvalidation.validators.NotEmptyValidator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CreateOfferView1 extends VBox {
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
                "Amount in EUR to spend",
                NotEmptyValidator.getInstance(), 
                DoubleValidator.getInstance()
        );


        saveButton = new Button("Next step");
        saveButton.setOnAction(e -> {
            System.out.println(model.createOffer());
        });

        getChildren().addAll(amountBox, marketPricePercentageBox, volumeBox, saveButton);
        setSpacing(20);
        setPadding(new Insets(10, 10, 10, 10));
    }

    private void addBindings() {
        amountBox.textProperty().bindBidirectional(model.amount, new StringCoinConverter());
        marketPricePercentageBox.textProperty().bindBidirectional(model.marketPricePercentage);
        volumeBox.textProperty().bindBidirectional(model.volume);

        saveButton.disableProperty().bind(
                BindingUtils.createAnyNotNullBinding(
                        amountBox.errorProperty(),
                        marketPricePercentageBox.errorProperty(),
                        volumeBox.errorProperty()
                )
        );
    }
}
