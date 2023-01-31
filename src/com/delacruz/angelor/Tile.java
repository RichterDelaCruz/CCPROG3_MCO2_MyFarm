package com.delacruz.angelor;

/**
 * This class represents the tile which has a status (Unplowed, plowed, or occupied by rocks), and crop such as the
 * root crops, flowers, and fruit trees.
 * <p>
 * Last Updated: December 10, 2022
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */

import com.delacruz.angelor.crop.AbstractCrop;

/**
 * The `Tile` class represents an individual tile on the farm grid.
 * It has a status (unplowed, plowed, has rocks) and can have a crop
 * planted on it.
 */
public class Tile {
    private Status status;
    private AbstractCrop crop;

    /**
     * The Tile object sets its status to its default (unplowed)
     */
    public Tile() {
        this.status = Status.UNPLOWED;
    }

    /**
     * Returns the crop planted on the tile.
     *
     * @return the crop planted on the tile
     */
    public AbstractCrop getCrop() {
        return crop;
    }

    /**
     * Sets the crop planted on the tile.
     *
     * @param crop the crop to be planted on the tile
     */
    public void setCrop(AbstractCrop crop) {
        this.crop = crop;
    }

    /**
     * Returns the status of the tile.
     *
     * @return the status of the tile
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the tile.
     *
     * @param status the new status of the tile
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * The `Status` enum represents the different statuses that a tile
     * can have. It has a status symbol and a status name.
     */
    public enum Status {
        /**
         * Unplowed status.
         */
        UNPLOWED(" ", "Unplowed"),

        /**
         * Plowed status.
         */
        PLOWED("_", "Plowed"),

        /**
         * Has rocks status.
         */
        HASROCKS("O", "Has Rocks");

        /**
         * The status symbol.
         */
        private final String statusSymbol;

        /**
         * The status name.
         */
        private final String statusName;

        /**
         * The constructor for the `Status` enum. It takes in a status
         * symbol and a status name and sets them as the values for the
         * enum instance.
         *
         * @param statusSymbol the symbol of the status
         * @param statusName   the name of the status
         */
        Status(String statusSymbol, String statusName) {
            this.statusSymbol = statusSymbol;
            this.statusName = statusName;
        }

        /**
         * The symbol used to represent this status.
         *
         * @return the status symbol of the tile
         */
        public String getStatusSymbol() {
            return statusSymbol;
        }

        /**
         * The name of this status.
         *
         * @return the status name of the tile
         */
        public String getStatusName() {
            return statusName;
        }


    }
}
