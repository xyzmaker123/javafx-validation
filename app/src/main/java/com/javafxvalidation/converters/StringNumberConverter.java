package com.javafxvalidation.converters;

import com.javafxvalidation.utils.ValidationUtils;
import javafx.util.StringConverter;

public class StringNumberConverter extends StringConverter<Number> {
    @Override
    public String toString(Number number) {
        return number != null ? number.toString() : "";
    }

    @Override
    public Number fromString(String value) {
        return ValidationUtils.isValidDouble(value) ? Double.parseDouble(value) : 0.0;
    }
}
