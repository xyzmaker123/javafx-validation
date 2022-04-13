package com.xyzmaker123.jfxvalidation.demo.views;

import com.xyzmaker123.jfxvalidation.controls.BasicTextFieldWrapper;
import com.xyzmaker123.jfxvalidation.converters.StringCoinConverter;
import com.xyzmaker123.jfxvalidation.converters.StringNumberConverter;
import com.xyzmaker123.jfxvalidation.utils.BindingUtils;
import com.xyzmaker123.jfxvalidation.validators.CoinValidator;
import com.xyzmaker123.jfxvalidation.validators.DoubleValidator;
import com.xyzmaker123.jfxvalidation.validators.NotEmptyValidator;
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
        volumeBox = new BasicTextFieldWrapper("Amount in USD to spend");

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
        marketPricePercentageBox.textProperty().bindBidirectional(model.marketPricePercentage, new StringNumberConverter());
        volumeBox.textProperty().bindBidirectional(model.volume, new StringNumberConverter());
        
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
