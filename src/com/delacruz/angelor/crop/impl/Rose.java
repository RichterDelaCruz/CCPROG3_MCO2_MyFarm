package com.delacruz.angelor.crop.impl;

import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;

/**
 * This is the properties of Rose
 *
 * <p>This class extends the `AbstractCrop` class and represents a type of flower crop.
 * It has specific properties such as name, growth rate, and yield, that are set in the
 * constructor.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class Rose extends AbstractCrop {

    /**
     * Constructs a new Rose crop with the given properties.
     */
    public Rose() {
        super("Rose", "R", CropType.FLOWER, 1, 1,
                2, 0, 1, 1, 1, 1, 5,
                5, 2.5, 0, 0,
                0);
    }

}
