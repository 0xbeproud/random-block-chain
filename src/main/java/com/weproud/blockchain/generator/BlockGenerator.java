package com.weproud.blockchain.generator;

import com.weproud.blockchain.block.Block;
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
        return Block.generateGenesis();
    }

    public static Block generate(final Block previousBlock, final List<Transaction> transactions) {
        int index = previousBlock.getIndex() + 1;
        Long timestamp = System.currentTimeMillis();
        String previousBlockHash = previousBlock.getHash();
        String hash = Block.generateBlockHash(index, previousBlockHash, timestamp, transactions);
        return Block.generate(index, previousBlockHash, hash, timestamp, transactions);
    }
}
