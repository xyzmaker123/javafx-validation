package com.javafxvalidationbisqdemo.views;

import com.javafxvalidation.controls.BasicTextFieldWrapper;
import com.javafxvalidation.converters.StringCoinConverter;
import com.javafxvalidation.utils.BindingUtils;
import com.javafxvalidation.validators.CoinValidator;
import com.javafxvalidation.validators.DoubleValidator;
import com.javafxvalidation.validators.NotEmptyValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CreateOfferView2 extends VBox {
    private final CreateOfferModel model;
    private BasicTextFieldWrapper amountBox, marketPricePercentageBox, volumeBox;
    private Button saveButton;

    // Validation result
    StringProperty amountValidationResult = new SimpleStringProperty(null);
    StringProperty marketPricePercentageValidationResult = new SimpleStringProperty(null);
    StringProperty volumeValidationResult = new SimpleStringProperty(null);

    public CreateOfferView2() {
        super();
        model = new CreateOfferModel();
        initialize();
        addBindings();
    }

    void initialize() {
        amountBox = new BasicTextFieldWrapper("Amount of BTC to buy");
        marketPricePercentageBox = new BasicTextFieldWrapper("Below % from market price");
        volumeBox = new BasicTextFieldWrapper("Amount in EUR to spend");

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
        
        amountBox.errorProperty().bind(amountValidationResult);
        marketPricePercentageBox.errorProperty().bind(marketPricePercentageValidationResult);
        volumeBox.errorProperty().bind(volumeValidationResult);

        amountValidationResult.bind(
                BindingUtils.createValidationBinding(
                        amountBox.textProperty(),
                        NotEmptyValidator.getInstance(),
                        CoinValidator.getInstance()
                )
        );

        marketPricePercentageValidationResult.bind(
                BindingUtils.createValidationBinding(
                        marketPricePercentageBox.textProperty(),
                        NotEmptyValidator.getInstance(),
                        DoubleValidator.getInstance()
                )
        );

        volumeValidationResult.bind(
                BindingUtils.createValidationBinding(
                        volumeBox.textProperty(),
                        NotEmptyValidator.getInstance(),
                        DoubleValidator.getInstance()
                )
        );

        saveButton.disableProperty().bind(
                BindingUtils.createAnyNotNullBinding(
                        amountValidationResult,
                        marketPricePercentageValidationResult,
                        volumeValidationResult
                )
        );
    }
}