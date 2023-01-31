package com.delacruz.angelor.crop.impl;

import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;

/**
 * This is the properties of Sunflower
 *
 * <p>This class extends the `AbstractCrop` class and represents a type of flower crop.
 * It has specific properties such as name, growth rate, and yield, that are set in the
 * constructor.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class Sunflower extends AbstractCrop {

    /**
     * Constructs a new Sunflower crop with the given properties.
     */
    public Sunflower() {
        super("Sunflower", "S", CropType.FLOWER, 3, 2,
                3, 1, 2, 1, 1, 1, 20,
                19, 7.5, 0, 0,
                0);
    }

}
