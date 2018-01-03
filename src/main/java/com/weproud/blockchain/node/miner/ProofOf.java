package com.weproud.blockchain.node.miner;

import com.weproud.blockchain.security.Security;

/**
 * @author Logan. 81k
 */

public class ProofOf {
    private ProofOf() {

    }

    public static final String work(final String blockHash, final long nonce) {
        return Security.calculateHash(String.format("%s%d", blockHash, nonce));
    }

    public static boolean verify(final String answer) {
        return !answer.startsWith("0");
    }
}
