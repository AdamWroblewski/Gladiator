package com.company;

import java.util.Collections;
import java.util.Random;

public class Utils {
    public static Random random = new Random();

    public enum Multiplier {
        Low(0.75),
        Medium(1.0),
        High(1.25);

        private double value;

        Multiplier(double value){
            this.value = value;
        }

        public static double getValue(Multiplier multiplier){
            return multiplier.value;
        }
    }

//
//    /**
//     * Returns multiplier value for given Multiplier enum
//     *
//     * @param mul
//     * @return
//     */
//    public static double getValue(Multiplier mul) {
//        switch (mul) {
//            case Low:
//                return 0.75;
//            case Medium:
//                return 1;
//            case High:
//                return 1.25;
//            default:
//                throw new IllegalArgumentException();
//        }
//    }

    /**
     * Returns a random value from given collection
     */
    public static String getRandomValue(Iterable<String> iterable) {
        var array = Collections.singletonList(iterable);
        int index = random.nextInt(array.size());

        return array.get(index).toString();
    }
}
