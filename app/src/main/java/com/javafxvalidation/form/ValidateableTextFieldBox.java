package com.javafxvalidation.form;

import com.javafxvalidation.core.Validator;
import com.javafxvalidation.utils.ValidationUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidateableTextFieldBox extends VBox {
    private final DisplayErrorStrategy displayErrorStrategy = DisplayErrorStrategy.ON_BLUR;
    
    private final TextField input;
    private final Label errorInfo;
    
    private final BooleanProperty touchedProperty = new SimpleBooleanProperty(false);
    private final ObservableList<String> errors = FXCollections.observableArrayList();

    public ValidateableTextFieldBox(Builder builder) {
        super();
        setMaxWidth(300);
        input = new TextField();
        errorInfo = new Label();
        
        getChildren().addAll(
                new Label(builder.label),
                input, 
                errorInfo
        );

        validate(builder.validators);
        
        input.textProperty().addListener((o, oldValue, focused) -> {
            validate(builder.validators);
        });
        
        input.focusedProperty().addListener((e, o, n) -> {
            if (n && !touchedProperty.get()) {
                touchedProperty.set(true);
            }
        });
        
        addBindings();
    }

    private void validate(List<Validator> validators) {
        errors.setAll(
                validators
                        .stream()
                        .filter(v -> !v.validate(input.getText()))
                        .map(Validator::getCode)
                        .collect(Collectors.toList())
        );
    }

    public StringProperty textProperty() {
        return this.input.textProperty();
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
    
    private void addBindings() {
        errorInfo.visibleProperty().bind(Bindings.createBooleanBinding(this::shouldDisplayErrorInfo, errors, input.focusedProperty()));
        errorInfo.textProperty().bind(Bindings.createStringBinding(this::getErrorMessage, errors));
    }

    private boolean shouldDisplayErrorInfo() {
        if (errors.isEmpty()) return false;
        return 
                displayErrorStrategy == DisplayErrorStrategy.IMMEDIATELY || 
                        displayErrorStrategy == DisplayErrorStrategy.ON_BLUR && touchedProperty.get();
        
    }

    private String getErrorMessage() {
        return ValidationUtils.getErrorMessage(errors);
    }

    enum DisplayErrorStrategy {
        IMMEDIATELY,
        ON_BLUR
    }

    public static class Builder {
        private final String label;
        private final List<Validator> validators = new ArrayList<>();

        public Builder(String label) {
            this.label = label;
        }
        
        public Builder validator(Validator validator) {
            this.validators.add(validator);
            return this;
        }
        
        public ValidateableTextFieldBox build() {
            return new ValidateableTextFieldBox(this);
    
        }
    }
}
