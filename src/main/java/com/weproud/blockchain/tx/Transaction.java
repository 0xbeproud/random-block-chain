package com.weproud.blockchain.tx;

import com.weproud.blockchain.security.Security;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Logan. 81k
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    private String hash;
    private Long timestamp;
    private List<Integer> randoms;

    public Transaction(final List<Integer> randoms) {
        this.randoms = randoms;
        this.timestamp = System.currentTimeMillis();
        this.hash = Security.calculateTransactionHash(this.hash, this.timestamp, this.randoms);
    }

    public int size(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.hash).append(timestamp);
        randoms.forEach(r -> sb.append(r));
        return sb.toString().getBytes().length;
    }
}
