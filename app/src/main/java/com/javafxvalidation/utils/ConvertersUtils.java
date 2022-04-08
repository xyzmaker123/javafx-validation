package com.javafxvalidation.utils;

import javafx.util.StringConverter;

public class ConvertersUtils {
    public static StringConverter<Number> getStringDoubleConverter() {
        return new StringConverter<>() {
            @Override
            public Number fromString(String s) {
                return Double.parseDouble(s);
            }

            @Override
            public String toString(Number i) {
                return i.toString();
            }
        };
    }
}
