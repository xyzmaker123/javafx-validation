package com.xyzmaker123.jfxvalidation.core;

public interface Validator {
    boolean validate(String text);

    String getMessage();
}
