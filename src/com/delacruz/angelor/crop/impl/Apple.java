package com.delacruz.angelor.crop.impl;

import com.delacruz.angelor.constant.FarmConstants;
import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;

/**
 * This is the properties of Apple
 *
 * <p>This class extends the `AbstractCrop` class and represents a type of fruit tree crop.
 * It has specific properties such as name, growth rate, and yield, that are set in the
 * constructor.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class Apple extends AbstractCrop {

    /**
     * Constructs a new Apple crop with the given properties.
     */
    public Apple() {
        super("Apple", "A", CropType.FRUIT_TREE, 10, 7,
                7, 5, 5, 10, 15, FarmConstants.getRandomNumber(10, 15), 200,
                5, 25, 0, 0,
                0);
    }

}