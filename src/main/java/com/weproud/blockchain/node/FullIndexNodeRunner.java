package com.weproud.blockchain.node;

import com.weproud.blockchain.block.Block;
import com.weproud.blockchain.chain.BlockChain;
import com.weproud.blockchain.generator.BlockGenerator;
import com.weproud.blockchain.node.miner.Miner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author Logan. 81k
 */

@Slf4j
@Component
public class FullIndexNodeRunner {

    @Autowired
    private Miner miner;

    @Autowired
    private BlockChain blockChain;

    public void run() {
        log.info("- start run");

        Block genesis = BlockGenerator.genesisBlock();
        this.blockChain.add(genesis);

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Block block = this.miner.mining();
            if (ObjectUtils.isEmpty(block))
                continue;

            log.info("send block to others");
        }
    }
}
