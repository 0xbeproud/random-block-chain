package com.weproud.blockchain.block;

import com.weproud.blockchain.security.Security;
import com.weproud.blockchain.tx.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Logan. 81k
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Block {
    public static final int GENESIS_BLOCK_INDEX = 0;
    public static final String GENESIS_BLOCK_PREVIOUS_HASH = "0";
    public static final int GENESIS_BLOCK_NUMBER_OF_TRANSACTIONS = 0;
    public static final long GENESIS_BLOCK_NONCE = 0L;
    private int index;
    private String previousHash;
    private long timestamp;
    private String hash;
    private List<Transaction> transactions;
    private int numberOfTransactions;
    private long nonce;
    private int size;

    public static Block generateGenesis() {
        long timestamp = System.currentTimeMillis();
        String hash = Block.generateBlockHash(GENESIS_BLOCK_INDEX, GENESIS_BLOCK_PREVIOUS_HASH, timestamp, null);
        Block block = Block.builder()
                .index(GENESIS_BLOCK_INDEX)
                .previousHash(GENESIS_BLOCK_PREVIOUS_HASH)
                .timestamp(System.currentTimeMillis())
                .hash(hash)
                .transactions(null)
                .numberOfTransactions(GENESIS_BLOCK_NUMBER_OF_TRANSACTIONS)
                .nonce(GENESIS_BLOCK_NONCE)
                .build();
        block.calculateSize();
        return block;
    }

    public static Block generate(final int index, final String previousBlockHash, final String hash, final Long timestamp, final List<Transaction> transactions) {
        Block block = Block.builder()
                .index(index)
                .previousHash(previousBlockHash)
                .timestamp(timestamp)
                .hash(hash)
                .transactions(transactions)
                .numberOfTransactions(transactions.size())
                .nonce(0L)
                .build();
        block.calculateSize();
        return block;
    }

    // shot-me: size를 이렇게 구하는게 맞는건가..?
    private void calculateSize() {
        final String sb = String.valueOf(this.index) +
                this.previousHash +
                this.timestamp +
                this.hash +
                this.numberOfTransactions +
                this.nonce;

        if (!CollectionUtils.isEmpty(transactions))
            this.size += transactions.stream().mapToInt(Transaction::size).sum();

        this.size += sb.getBytes().length;
    }

    public void updateNonce(final long nonce) {
        this.nonce = nonce;
    }

    public static String generateBlockHash(int index, String previousHash, Long timestamp, List<Transaction> transactions) {
        String merkleRootHash = Block.generateMerkleRootHash(transactions);
        return Security.generateHash(String.valueOf(index), previousHash, String.valueOf(timestamp), merkleRootHash);
    }

    // shot-me: 일단 흉내내기만.. 추후에 변경해야함.
    public static String generateMerkleRootHash(final List<Transaction> tx) {
        if (ObjectUtils.isEmpty(tx))
            return "";
        return Security.calculateHash(tx.stream().map(Transaction::getHash).collect(Collectors.joining()));
    }
}
