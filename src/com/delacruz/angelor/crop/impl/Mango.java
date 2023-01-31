package com.delacruz.angelor.crop.impl;

import com.delacruz.angelor.constant.FarmConstants;
import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;

/**
 * This is the properties of Mango
 *
 * <p>This class extends the `AbstractCrop` class and represents a type of fruit tree crop.
 * It has specific properties such as name, growth rate, and yield, that are set in the
 * constructor.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class Mango extends AbstractCrop {

    /**
     * Constructs a new Mango crop with the given properties.
     */
    public Mango() {
        super("Mango", "M", CropType.FRUIT_TREE, 10, 7,
                7, 4, 4, 5, 15, FarmConstants.getRandomNumber(5, 15), 100,
                8, 25, 0, 0,
                0);
    }

}
