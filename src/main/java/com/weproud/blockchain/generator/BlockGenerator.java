package com.weproud.blockchain.generator;

import com.weproud.blockchain.block.Block;
import com.weproud.blockchain.security.Security;
import com.weproud.blockchain.tx.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Logan. 81k
 */
@Slf4j
public class BlockGenerator {
    private BlockGenerator(){

    }

    public static Block generateGenesis() {
        int index = 0;
        Long timestamp = System.currentTimeMillis();
        String hash = Security.generateBlockHash(index, "0", timestamp, null);
        Block block = Block.builder()
                .index(index)
                .previousHash("0")
                .timestamp(timestamp)
                .hash(hash)
                .transactions(null)
                .numberOfTransactions(0)
                .nonce(0L)
                .build();
        block.calculateSize();
        log.info("create genesis block: {}", block);
        return block;
    }

    public static Block generate(final Block previousBlock, final List<Transaction> transactions) {
        int index = previousBlock.getIndex() + 1;
        Long timestamp = System.currentTimeMillis();
        String hash = Security.generateBlockHash(index, previousBlock.getHash(), timestamp, transactions);
        Block block = Block.builder()
                .index(index)
                .previousHash(previousBlock.getHash())
                .timestamp(timestamp)
                .hash(hash)
                .transactions(transactions)
                .numberOfTransactions(transactions.size())
                .build();
        block.calculateSize();
        return block;
    }
}
