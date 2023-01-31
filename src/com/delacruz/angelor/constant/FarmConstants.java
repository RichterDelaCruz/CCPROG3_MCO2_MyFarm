package com.delacruz.angelor.constant;

import java.util.Random;

/**
 * A class that defines farm-related constants and utility methods.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class FarmConstants {

    /**
     * The number of rows in the farm.
     */
    public static final int ROW = 10;

    /**
     * The number of columns in the farm.
     */
    public static final int COLUMN = 5;

    /**
     * Generates a random number within the specified range.
     *
     * @param min the minimum value of the range (inclusive)
     * @param max the maximum value of the range (inclusive)
     * @return the generated random number
     */
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max + 1).findFirst().getAsInt();
    }
}
