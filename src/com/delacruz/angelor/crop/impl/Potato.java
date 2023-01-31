package com.delacruz.angelor.crop.impl;

import com.delacruz.angelor.constant.FarmConstants;
import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;

/**
 * This is the properties of Potato
 *
 * <p>This class extends the `AbstractCrop` class and represents a type of root crop.
 * It has specific properties such as name, growth rate, and yield, that are set in the
 * constructor.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class Potato extends AbstractCrop {

    /**
     * Constructs a new Potato crop with the given properties.
     */
    public Potato() {
        super("Potato", "P", CropType.ROOT_CROP, 5, 3,
                4, 1, 2, 1, 10, FarmConstants.getRandomNumber(1, 10), 20,
                3, 12.5, 0, 0,
                0);
    }

}
