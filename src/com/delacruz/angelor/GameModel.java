package com.delacruz.angelor;

import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.impl.*;
import com.delacruz.angelor.farmertype.Farmer;
import com.delacruz.angelor.farmertype.FarmerType;

/**
 * The `GameModel` class represents a game model that includes a farmer and a farm.
 *
 * @author Your Name
 * @version 1.0
 */
public class GameModel {
    private Farmer farmer;
    private int tileRow;
    private int tileColumn;

    private AbstractCrop crop;

    private Farmer farmerTemp;

    /**
     * Constructs a new `GameModel` object and initializes the farmer with default values for coins and experience.
     */
    public GameModel() {
        this.farmer = new Farmer();
        this.farmer.setCoins(1000);
        this.farmer.setExperience(1000);
    }

    /**
     * Returns the `Farmer` object associated with this `GameModel`.
     *
     * @return the `Farmer` object associated with this `GameModel`
     */
    public Farmer getFarmer() {
        return farmer;
    }

    /**
     * Sets the `Farmer` object associated with this `GameModel`.
     *
     * @param farmer the `Farmer` object to be associated with this `GameModel`
     */
    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    /**
     * Returns the number of rows of tiles in the game.
     *
     * @return the number of rows of tiles in the game
     */
    public int getTileRow() {
        return tileRow;
    }

    /**
     * Sets the number of rows of tiles in the game.
     *
     * @param tileRow the number of rows of tiles in the game
     */
    public void setTileRow(int tileRow) {
        this.tileRow = tileRow;
    }

    /**
     * Returns the number of columns of tiles in the game.
     *
     * @return the number of columns of tiles in the game
     */
    public int getTileColumn() {
        return tileColumn;
    }

    /**
     * Sets the number of columns of tiles in the game.
     *
     * @param tileColumn the number of columns of tiles in the game
     */
    public void setTileColumn(int tileColumn) {
        this.tileColumn = tileColumn;
    }

    /**
     * Returns a `Turnip` object representing a turnip crop.
     *
     * @return a `Turnip` object representing a turnip crop
     */
    public AbstractCrop getTurnipCrop() {
        return this.crop = new Turnip();
    }

    /**
     * Returns a `Carrot` object representing a carrot crop.
     *
     * @return a `Carrot` object representing a carrot crop
     */
    public AbstractCrop getCarrotCrop() {
        return this.crop = new Carrot();
    }

    /**
     * Returns a `Carrot` object representing a carrot crop.
     *
     * @return a `Carrot` object representing a carrot crop
     */
    public AbstractCrop getPotatoCrop() {
        return this.crop = new Potato();
    }

    /**
     * Returns a `Rose` object representing a rose crop.
     *
     * @return a `Rose` object representing a rose crop
     */
    public AbstractCrop getRoseCrop() {
        return this.crop = new Rose();
    }

    /**
     * Returns a `Turnips` object representing a turnips crop.
     *
     * @return a `Turnips` object representing a turnips crop
     */
    public AbstractCrop getTurnipsCrop() {
        return this.crop = new Turnips();
    }

    /**
     * Returns a `Sunflower` object representing a sunflower crop.
     *
     * @return a `Sunflower` object representing a sunflower crop
     */
    public AbstractCrop getSunflowerCrop() {
        return this.crop = new Sunflower();
    }

    /**
     * Returns a `Mango` object representing a mango crop.
     *
     * @return a `Mango` object representing a mango crop
     */
    public AbstractCrop getMangoCrop() {
        return this.crop = new Mango();
    }

    /**
     * Returns a `Apple` object representing an apple crop.
     *
     * @return a `Apple` object representing an apple crop
     */
    public AbstractCrop getAppleCrop() {
        return this.crop = new Apple();
    }

    /**
     * Returns the `FarmerTemp` object associated with this `GameModel`.
     *
     * @return the `FarmerTemp` object associated with this `GameModel`
     */
    public Farmer getFarmerTemp() {
        return this.farmerTemp;
    }

    /**
     * Returns a `Farmer` object with `REGISTERED` type.
     *
     * @return a `Farmer` object with `REGISTERED` type
     */
    public Farmer getFarmerRegistered() {
        farmerTemp = new Farmer();
        this.farmerTemp.setFarmerType(FarmerType.REGISTERED);
        return this.farmerTemp;
    }

    /**
     * Returns a `Farmer` object with `DISTINGUISHED` type.
     *
     * @return a `Farmer` object with `DISTINGUISHED` type
     */
    public Farmer getFarmerDistinguished() {
        farmerTemp = new Farmer();
        this.farmerTemp.setFarmerType(FarmerType.DISTINGUISHED);
        return this.farmerTemp;
    }

    /**
     * Returns a `Farmer` object with `LEGENDARY` type.
     *
     * @return a `Farmer` object with `LEGENDARY` type
     */
    public Farmer getFarmerLegendary() {
        farmerTemp = new Farmer();
        this.farmerTemp.setFarmerType(FarmerType.LEGENDARY);
        return this.farmerTemp;
    }
}
