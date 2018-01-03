package com.weproud.api.generator;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Logan. 81k
 */
public class RandomGenerator {
    private RandomGenerator(){

    }

    public static List<Integer> generateRandomsBy(final int origin, final int bound, final int limit) {
        List<Integer> randoms = new Random()
                .ints(origin, bound)
                .limit(limit)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(randoms);
        return randoms;
    }
}
