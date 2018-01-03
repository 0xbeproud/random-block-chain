package com.weproud.blockchain.tx;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Logan. 81k
 */
@Getter
@Slf4j
public class TransactionPool {
    private ArrayList<Transaction> transactions;

    public TransactionPool() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactionsBy(final String s) {
        return (List<Transaction>) this.transactions.clone();
    }

    public void add(final Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void removeAll(final List<Transaction> transactions) {
        this.transactions.removeAll(transactions);
        log.info("remove all transactions already used");
    }
}
