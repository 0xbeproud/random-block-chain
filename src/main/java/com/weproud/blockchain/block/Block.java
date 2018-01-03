package com.weproud.blockchain.block;

import com.weproud.blockchain.tx.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Logan. 81k
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Block {
    private int index;
    private String previousHash;
    private long timestamp;
    private String hash;
    private List<Transaction> transactions;
    private int numberOfTransactions;
    private long nonce;
    private int size;

    public void calculateSize() {
        final StringBuilder sb = new StringBuilder();
        sb.append(index)
            .append(previousHash)
            .append(timestamp)
            .append(hash)
                .append(numberOfTransactions)
            .append(nonce);

        if(!CollectionUtils.isEmpty(transactions))
            transactions.forEach(tx -> sb.append(tx.size()));

        this.size = sb.toString().getBytes().length;
    }

    public void updateNonce(final long nonce) {
        this.nonce = nonce;
    }
}
