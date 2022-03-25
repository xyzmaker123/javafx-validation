package com.javafxvalidation.utils;

import com.javafxvalidation.core.Validator;
import com.javafxvalidation.form.ValidateableTextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class FormBuilder {
    public static VBox addTextFieldBox(Validator ...validators) {
        ValidateableTextField input = new ValidateableTextField();
        input.setMaxWidth(300);
        
        for (Validator validator : validators) {
            input.registerValidator(validator);
        }

        Label errorInfo = new Label();
        errorInfo.setVisible(false);


        input.registerValidationListener((isValid, errorMessage) -> {
            errorInfo.setVisible(!isValid);
            errorInfo.setText(errorMessage);
        });
        
        VBox box = new VBox();
        box.getChildren().add(input);
        box.getChildren().add(errorInfo);
        
        return box;
    }
}
