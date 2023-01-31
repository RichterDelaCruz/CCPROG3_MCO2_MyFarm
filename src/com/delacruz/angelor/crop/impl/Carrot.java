package com.delacruz.angelor.crop.impl;

import com.delacruz.angelor.constant.FarmConstants;
import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;

/**
 * This is the properties of Carrot
 *
 * <p>This class extends the `AbstractCrop` class and represents a type of root crop.
 * It has specific properties such as name, growth rate, and yield, that are set in the
 * constructor.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class Carrot extends AbstractCrop {

    /**
     * Constructs a new Carrot crop with the given properties.
     */
    public Carrot() {
        super("Carrot", "C", CropType.ROOT_CROP, 3, 1,
                2, 0, 1, 1, 2, FarmConstants.getRandomNumber(1, 2), 10,
                9, 7.5, 0, 0,
                0);
    }

}

