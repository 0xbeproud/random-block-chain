package com.weproud.api.service;

import com.weproud.api.exception.TransactionNotFoundException;
import com.weproud.blockchain.block.Block;
import com.weproud.blockchain.chain.BlockChain;
import com.weproud.blockchain.tx.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author Logan. 81k
 */
@Slf4j
@Service
public class TransactionService {
    @Autowired
    private BlockChain blockChain;

    public Transaction getTransaction(final String hash) {
        log.info("getTransaction by: {}", hash);

        List<Block> blocks = this.blockChain.getChains();
        log.info("block chain size: {}", blocks.size());
        for (final Block block : blocks) {
            List<Transaction> transactions = block.getTransactions();
            if (CollectionUtils.isEmpty(transactions)) // genesis block doesn't have transaction
                continue;

            Optional<Transaction> transactionOptional = transactions.stream()
                    .filter(tx -> tx.getHash().equals(hash))
                    .findFirst();
            if (transactionOptional.isPresent())
                return transactionOptional.get();
        }
        throw new TransactionNotFoundException();
    }
}
