package com.helloworld.transactions;

import java.util.Arrays;
import java.util.List;

public class TransactionsServiceDefaultImpl implements TransactionsService {

    @Override
    public List<String> getTransactions() {
        return Arrays.asList("Transaction A", "Transaction B");
    }
}
