package com.company;

import java.util.Collections;
import java.util.Random;

public class Utils {
    public static Random random = new Random();

    public enum Multiplier {
        Low,
        Medium,
        High;
    }
    /**
     * Returns multiplier value for given Multiplier enum
     *
     * @param mul
     * @return
     */
    public static double getValue(Multiplier mul) {
        switch (mul) {
            case Low:
                return 0.75;
            case Medium:
                return 1;
            case High:
                return 1.25;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Returns a random value from given collection
     */
    public static String getRandomValue(Iterable<String> iterable) {
        var array = Collections.singletonList(iterable);
        int index = random.nextInt(array.size());

        return array.get(index).toString();
    }
}
