package com.weproud.api.service;

import com.weproud.api.generator.RandomGenerator;
import com.weproud.blockchain.tx.Transaction;
import com.weproud.blockchain.tx.TransactionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Logan. 81k
 */
@Service
public class RandomService {

    private static final int DEFAULT_ORIGIN = 0;
    private static final int DEFAULT_BOUND = 100;
    private final TransactionPool transactionPool;

    @Autowired
    public RandomService(TransactionPool transactionPool) {
        this.transactionPool = transactionPool;
    }

    public Transaction getRandomNumber(final int bound, final int limit) {

        List<Integer> randoms = RandomGenerator.generateRandomsBy(DEFAULT_ORIGIN, bound, limit);
        Transaction transaction = new Transaction(randoms);
        this.transactionPool.add(transaction);
        return transaction;
    }

    public Transaction getRandomNumber(final int limit) {

        List<Integer> randoms = RandomGenerator.generateRandomsBy(DEFAULT_ORIGIN, DEFAULT_BOUND, limit);
        Transaction transaction = new Transaction(randoms);
        this.transactionPool.add(transaction);
        return transaction;
    }
}
