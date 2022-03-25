package com.helloworld;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        JavaFXApplicationModule module = new JavaFXApplicationModule();
        Injector injector = Guice.createInjector(module);
        
        JavaFXApplication.setInjector(injector);
        Application.launch(JavaFXApplication.class, args);
    }
}
