package com.javafxvalidation.core;

public interface Validator {
    boolean validate(String text);

    String getCode();
    
}
