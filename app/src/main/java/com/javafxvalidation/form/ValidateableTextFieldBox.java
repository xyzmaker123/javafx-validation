package com.javafxvalidation.form;

import com.javafxvalidation.core.Validator;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.*;

public class ValidateableTextFieldBox extends VBox {
    private final TextField input;
    private final Label errorInfo;
    private final List<String> errors = new ArrayList<>();
    private final List<Validator> validators = new ArrayList<>();
    
    private Map<String, String> MESSAGES = new HashMap<>() {{
        put("NotEmpty", "This field cannot be empty");
        put("MinLength", "Value too short"); // TODO: setup minLength parameter
    }};

    public ValidateableTextFieldBox(Validator ...validators) {
        super();
        input = new TextField();
        errorInfo = new Label();
        errorInfo.setVisible(false);
        
        getChildren().add(input);
        getChildren().add(errorInfo);

        this.validators.addAll(Arrays.asList(validators));
        
        input.focusedProperty().addListener((o, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                validate();
            }
        });
    }
    
    private void validate() {
        errors.clear();
        for (Validator validator : validators) {
            if (!validator.validate(input.getText())) {
                errors.add(validator.getCode());
            }
        }

        boolean isValid = errors.size()  == 0;
        String errorMessage = isValid ? null: MESSAGES.get(errors.get(0));

        errorInfo.setVisible(!isValid);
        errorInfo.setText(errorMessage);
    }
}
