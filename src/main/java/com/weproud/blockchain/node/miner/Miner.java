package com.weproud.blockchain.node.miner;

import com.weproud.blockchain.block.Block;
import com.weproud.blockchain.chain.BlockChain;
import com.weproud.blockchain.generator.BlockGenerator;
import com.weproud.blockchain.tx.Transaction;
import com.weproud.blockchain.tx.TransactionPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Logan. 81k
 */
@Slf4j
@Component
public class Miner {

    @Autowired
    private BlockChain blockChain;

    @Autowired
    private TransactionPool transactionPool;

    public Block mining() {
        log.info("start mining");
        List<Transaction> tx = this.transactionPool.getTransactionsBy("high-fee");
        if(CollectionUtils.isEmpty(tx))
            return null;

        log.info("- get txs: {}", tx);

        long nonce = 0;
        String answer = null;
        Block block = BlockGenerator.generate(this.blockChain.getLatestBlock(), tx);
        do{
            answer = ProofOf.work(block.getHash(), nonce++);
        }while (ProofOf.verify(answer));
        log.info("answer is {}", answer);

        this.transactionPool.removeAll(tx);
        block.updateNonce(nonce);
        this.blockChain.add(block);

        return block;
    }
}
