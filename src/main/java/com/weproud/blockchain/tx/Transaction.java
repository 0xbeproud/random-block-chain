package com.weproud.blockchain.tx;

import com.weproud.blockchain.security.Security;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

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
        this.hash = Transaction.calculateTransactionHash(this.hash, this.timestamp, this.randoms);
    }

    public int size(){
        int size = String.valueOf(this.timestamp).length() + this.hash.length();
        if (!ObjectUtils.isEmpty(randoms))
            size += randoms.stream().map(String::valueOf).mapToInt(String::length).sum();
        return size;
    }

    private static String calculateTransactionHash(final String hash, final long timestamp, final List<Integer> randoms) {
        String joining = randoms.stream().map(String::valueOf).collect(Collectors.joining());
        return Security.generateHash(hash, String.valueOf(timestamp), joining);
    }
}
