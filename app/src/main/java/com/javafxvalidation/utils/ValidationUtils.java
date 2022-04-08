package com.javafxvalidation.utils;

import com.javafxvalidation.form.ValidateableTextFieldBox;
import com.javafxvalidation.validators.CoinValidator;
import com.javafxvalidation.validators.DoubleValidator;
import com.javafxvalidation.validators.MinLengthValidator;
import com.javafxvalidation.validators.NotEmptyValidator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationUtils {
    
    // TODO: fix open-closed principle
    private static final Map<String, String> MESSAGES = new HashMap<>() {{
        put(NotEmptyValidator.CODE, "This field cannot be empty");
        put(DoubleValidator.CODE, "No valid number");
        put(MinLengthValidator.CODE, "Value too short"); // TODO: setup minLength parameter
        put(CoinValidator.CODE, "No valid coin");
    }};
    
    public static String getErrorMessage(List<String> codes) {
        return codes.isEmpty() ? null : MESSAGES.get(codes.get(0));
    }

    public static BooleanBinding createValidationBindings(ValidateableTextFieldBox...boxes) {
        StringProperty[] dependencies = new StringProperty[boxes.length];
        Arrays.stream(boxes).map(ValidateableTextFieldBox::textProperty).toList().toArray(dependencies);

        return Bindings.createBooleanBinding(
                () -> Arrays.stream(boxes).anyMatch(box -> !box.isValid()),
                dependencies
        );
    }
}
