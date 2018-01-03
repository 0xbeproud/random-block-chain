package com.weproud.api.service;


import com.weproud.api.exception.BlockNotFoundException;
import com.weproud.blockchain.block.Block;
import com.weproud.blockchain.chain.BlockChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Logan. 81k
 */
@Service
public class BlockScanService {
    @Autowired
    private BlockChain blockChain;

    public Block getBlockByIndex(final Integer index) {
        return this.blockChain.getBlockByIndex(index);
    }

    public Block getBlockByHeight(final Integer height) {
        if(!this.blockChain.hasBlockByHeight(height))
            throw new BlockNotFoundException();

        return this.blockChain.getChains().get(height);
    }
}
