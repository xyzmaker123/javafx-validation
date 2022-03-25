package com.javafxvalidation.core;

public interface ValidationListener {
    void onValidationChange(boolean isValid, String errorMessage);
}
