package com.weproud.blockchain.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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

    // shot-me: 뭔가 엘레강스하지 않음.
    public static String generateHash(String... values) {
        return calculateHash(Arrays.stream(values).collect(Collectors.joining()));
    }
}
