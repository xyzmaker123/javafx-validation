package com.javafxvalidation.utils;

import com.javafxvalidation.core.Validator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.Arrays;

public class BindingUtils {
    public static ObservableValue<? extends String> createValidationBinding(StringProperty property, Validator ...validators) {

        return Bindings.createStringBinding(() -> {
            String error = Arrays.stream(validators)
                    .filter(v -> !v.validate(property.get()))
                    .map(Validator::getMessage)
                    .findFirst().orElse(null);
            
            return error;
        }, property);
    }
    
    public static BooleanBinding createAnyNotNullBinding(StringProperty ...properties) {
        return Bindings.createBooleanBinding(() -> {
            for (StringProperty property : properties) {
                if (property.get() != null) return true;
            }
            return false;
        }, properties);
    }
}
