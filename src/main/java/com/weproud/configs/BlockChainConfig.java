package com.weproud.configs;

import com.weproud.blockchain.block.Block;
import com.weproud.blockchain.chain.BlockChain;
import com.weproud.blockchain.generator.BlockGenerator;
import com.weproud.blockchain.node.FullIndexNodeRunner;
import com.weproud.blockchain.node.miner.Miner;
import com.weproud.blockchain.node.miner.ProofOf;
import com.weproud.blockchain.tx.TransactionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Logan. 81k
 */
@Configuration
public class BlockChainConfig {

    @Bean
    public FullIndexNodeRunner fullIndexNodeRunner(){
        return new FullIndexNodeRunner();
    }

    @Bean
    public BlockChain blockChain(){
        return new BlockChain();
    }

    @Bean
    public TransactionPool transactionPool(){
        return new TransactionPool();
    }

    @Bean
    public Miner miner(){
        return new Miner();
    }

}
