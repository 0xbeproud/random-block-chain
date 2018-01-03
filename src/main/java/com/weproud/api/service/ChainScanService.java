package com.weproud.api.service;

import com.weproud.blockchain.block.Block;
import com.weproud.blockchain.chain.BlockChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Logan. 81k
 */
@Service
public class ChainScanService {
    @Autowired
    private BlockChain blockChain;

    public List<Block> getChain() {
        return this.blockChain.getChains();
    }
}
