package com.weproud.blockchain.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SecurityTest {
    @Test
    public void generate_hash_from_seed() {
        System.out.println(Security.generateHash(String.valueOf(1), "a", "b", "c", "d"));
    }
}