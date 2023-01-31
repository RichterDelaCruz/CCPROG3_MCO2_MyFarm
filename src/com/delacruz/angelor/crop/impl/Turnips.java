package com.delacruz.angelor.crop.impl;

import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;

/**
 * This is the properties of Turnips
 *
 * <p>This class extends the `AbstractCrop` class and represents a type of flower crop.
 * It has specific properties such as name, growth rate, and yield, that are set in the
 * constructor.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class Turnips extends AbstractCrop {

    /**
     * Constructs a new Turnips crop with the given properties.
     */
    public Turnips() {
        super("Turnips", "U", CropType.FLOWER, 2, 2,
                3, 0, 1, 1, 1, 1, 10,
                9, 5, 0, 0,
                0);
    }

}
