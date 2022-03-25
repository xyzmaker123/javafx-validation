package com.helloworld.transactions;

import com.google.inject.AbstractModule;

public class TransactionsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransactionsService.class).to(TransactionsServiceDefaultImpl.class);
    }
}
