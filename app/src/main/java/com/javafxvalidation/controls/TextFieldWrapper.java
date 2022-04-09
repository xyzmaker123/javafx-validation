package com.javafxvalidation.controls;

import com.javafxvalidation.core.Validator;

import java.util.Arrays;

public class TextFieldWrapper extends BasicTextFieldWrapper {
    
    public TextFieldWrapper(String label, Validator ...validators) {
        super(label);

        validate(validators);
        input.textProperty().addListener((o, oldValue, focused) -> {
            validate(validators);
        });
    }

    private void validate(Validator [] validators) {
        String error = Arrays.stream(validators)
                .filter(v -> !v.validate(input.getText()))
                .map(Validator::getMessage)
                .findFirst().orElse(null);

        errorProperty.set(error);
    }
}
