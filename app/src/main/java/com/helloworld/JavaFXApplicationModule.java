package com.helloworld;

import com.google.inject.AbstractModule;
import com.helloworld.transactions.TransactionsModule;

public class JavaFXApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new TransactionsModule());
    }
}
