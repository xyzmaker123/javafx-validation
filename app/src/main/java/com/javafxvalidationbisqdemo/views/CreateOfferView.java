package com.javafxvalidationbisqdemo.views;

import com.javafxvalidation.converters.StringCoinConverter;
import com.javafxvalidation.form.ValidateableTextFieldBox;
import com.javafxvalidation.validators.CoinValidator;
import com.javafxvalidation.validators.DoubleValidator;
import com.javafxvalidation.validators.NotEmptyValidator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javafxvalidation.utils.ValidationUtils.createValidationBindings;

public class CreateOfferView extends VBox {
    private final CreateOfferModel model;
    private ValidateableTextFieldBox amountBox, marketPricePercentageBox, volumeBox;
    private Button saveButton;

    public CreateOfferView() {
        super();
        model = new CreateOfferModel();
        initialize();
        addBindings();
    }

    void initialize() {
        amountBox = new ValidateableTextFieldBox.Builder("Amount of BTC to buy")
                .validator(new NotEmptyValidator())
                .validator(new CoinValidator())
                .build();

        marketPricePercentageBox = new ValidateableTextFieldBox.Builder("Below % from market price")
                .validator(new NotEmptyValidator())
                .validator(new DoubleValidator())
                .build();

        volumeBox = new ValidateableTextFieldBox.Builder("Amount in EUR to spend")
                .validator(new NotEmptyValidator())
                .validator(new DoubleValidator())
                .build();


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
        
        saveButton.disableProperty().bind(createValidationBindings(amountBox, marketPricePercentageBox, volumeBox));
    }


    private void setupValidation() {
        StringProperty amountValidationResult = new SimpleStringProperty(null);
        StringProperty marketPricePercentageValidationResult = new SimpleStringProperty(null);
        StringProperty volumeValidationResult = new SimpleStringProperty(null);

        Map<ValidateableTextFieldBox, List<String>> rules = new HashMap<>() {{
            put(amountBox, List.of("NotEmpty", "Coin"));
            put(marketPricePercentageBox, List.of("NotEmpty", "Double"));
            put(volumeBox, List.of("NotEmpty", "Double"));
        }};

        rules.forEach((box, items) -> {
            box.textProperty().addListener((e, o, n) -> {
            });
        });

        BooleanBinding amountError = Bindings.isEmpty(amountValidationResult);
        BooleanBinding marketPricePercentageError = Bindings.isEmpty(marketPricePercentageValidationResult);
        BooleanBinding volumeError = Bindings.isEmpty(volumeValidationResult);

        ObservableMap<String, String> errors = FXCollections.observableHashMap();

        saveButton.disableProperty().bind(amountError.and(marketPricePercentageError).and(volumeError));
    }
}
