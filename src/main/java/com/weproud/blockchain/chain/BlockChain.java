package com.weproud.blockchain.chain;

import com.weproud.blockchain.block.Block;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Logan. 81k
 */
@Getter
@Component
public class BlockChain {
    public static final int GENESIS_BLOCK_INDEX = 0;

    private List<Block> chains;

    public BlockChain() {
        this.chains = new ArrayList<>();
    }

    public void add(final Block block) {
        this.chains.add(block);
    }

    public Block getGenesis(){
        return this.chains.get(GENESIS_BLOCK_INDEX);
    }

    public boolean hasGenesis(){
        return !ObjectUtils.isEmpty(this.getGenesis());
    }

    public Block getLatestBlock() {
        return this.chains.get(this.chains.size() - 1);
    }

    public Block getBlockByIndex(final int index) {
        if (this.chains.size() + 1 < index)
            throw new IllegalArgumentException();

        return this.chains.get(index);
    }

    public int height() {
        return this.chains.size() - 1;
    }

    public boolean hasBlockByHeight(final Integer height) {
        return this.chains.size() > height;
    }
}
