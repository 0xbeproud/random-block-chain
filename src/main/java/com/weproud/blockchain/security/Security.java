package com.weproud.blockchain.security;

import com.weproud.blockchain.tx.Transaction;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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

    public static String calculateBlockHash(int index, String previousHash, Long timestamp, List<Transaction> transactions) {
        String seed = String.format("%d%s%d%s", index, previousHash, timestamp, transactions);
        return calculateHash(seed);
    }

    public static String calculateTransactionHash(final String hash, final long timestamp, final List<Integer> randoms) {
        String seed = String.format("%s%d%s", hash, timestamp, randoms);
        return calculateHash(seed);
    }
}
