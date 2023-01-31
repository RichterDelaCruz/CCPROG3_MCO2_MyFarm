package com.delacruz.angelor.crop;

/**
 * Enumerates the different crop types in the game.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public enum CropType {
    /**
     * Root crop type.
     */
    ROOT_CROP("Root crop"),

    /**
     * Flower type.
     */
    FLOWER("Flower"),

    /**
     * Fruit tree type.
     */
    FRUIT_TREE("Fruit tree");

    /**
     * The name of the crop type.
     */
    private final String cropTypeName;

    /**
     * Constructs a new `CropType` with the given name.
     *
     * @param name the name of the crop type
     */
    CropType(String name) {
        this.cropTypeName = name;
    }

    /**
     * Returns the name of the crop type.
     *
     * @return the name of the crop type
     */
    public String getName() {
        return cropTypeName;
    }
}
