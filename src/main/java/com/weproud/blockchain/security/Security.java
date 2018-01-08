package com.weproud.blockchain.security;

import com.weproud.blockchain.tx.Transaction;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Logan. 81k
 */
public class Security {
    public static String calculateHash(final String seed) {
        String sha256 = null;
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(seed.getBytes("UTF-8"));
            byte hash[] = sh.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            sha256 = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha256;
    }

    public static String generateBlockHash(int index, String previousHash, Long timestamp, List<Transaction> transactions) {
        String merkleRootHash = Security.generateMerkleRootHash(transactions);
        String seed = generateHash(String.valueOf(index), previousHash, String.valueOf(timestamp), merkleRootHash);
        return calculateHash(seed);
    }

    public static String calculateTransactionHash(final String hash, final long timestamp, final List<Integer> randoms) {
        String joining = randoms.stream().map(String::valueOf).collect(Collectors.joining());
        return calculateHash(generateHash(hash, String.valueOf(timestamp), joining));
    }

    public static String generateHash(String... values) {
        return calculateHash(Arrays.stream(values).collect(Collectors.joining()));
    }

    // binary tree로 변환해야함. 일단 list로 처리.
    public static String generateMerkleRootHash(final List<Transaction> tx) {
        return calculateHash(tx.stream().map(Transaction::getHash).collect(Collectors.joining()));
    }
}
