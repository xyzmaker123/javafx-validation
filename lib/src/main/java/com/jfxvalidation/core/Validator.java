package com.jfxvalidation.core;

public interface Validator {
    boolean validate(String text);

    String getMessage();
}
