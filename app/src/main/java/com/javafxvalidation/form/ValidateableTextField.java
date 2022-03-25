package com.javafxvalidation.form;

import com.javafxvalidation.core.ValidationListener;
import com.javafxvalidation.core.Validator;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidateableTextField extends TextField {
    private final List<String> errors = new ArrayList<>();
    private final List<Validator> validators = new ArrayList<>();
    private final List<ValidationListener> validationListeners = new ArrayList<>();
    
    private Map<String, String> MESSAGES = new HashMap<>() {{
        put("NotEmpty", "This field cannot be empty");
        put("MinLength", "Value too short"); // TODO: setup minLength parameter
    }};

    public ValidateableTextField() {
        super();

        focusedProperty().addListener((o, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                validate();
            }
        });
    }

    public void registerValidator(Validator validator) {
        validators.add(validator);    
    }
    
    public void registerValidationListener(ValidationListener listener) {
        validationListeners.add(listener);
    }

    private void validate() {
        errors.clear();
        for (Validator validator : validators) {
            if (!validator.validate(getText())) {
                errors.add(validator.getCode());
            }
        }

        boolean isValid = errors.size()  == 0;
        String errorMessage = isValid ? null: MESSAGES.get(errors.get(0));

        for (ValidationListener listener: validationListeners) {
            listener.onValidationChange(isValid, errorMessage);
        }
    }
}
