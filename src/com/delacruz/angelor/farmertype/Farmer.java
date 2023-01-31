package com.delacruz.angelor.farmertype;

import com.delacruz.angelor.Tile;
import com.delacruz.angelor.constant.FarmConstants;

/**
 * This class represents the farmer which has a farmer type, name, days passed, experience, coins, and their farm (tiles).
 * Last Updated: December 10, 2022
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class Farmer {

    /**
     * The farm tiles of the farmer.
     */
    private final Tile[][] tile = new Tile[FarmConstants.ROW][FarmConstants.COLUMN];

    /**
     * The type of the farmer.
     */
    protected FarmerType farmerType;

    /**
     * The name of the farmer.
     */
    private String name;

    /**
     * The number of days the farmer has spent on the farm.
     */
    private int days;

    /**
     * The farming experience of the farmer.
     */
    private double experience;

    /**
     * The coins of the farmer.
     */
    private double coins;

    /**
     * The farmer object creates the farmer object by supplying the farmer type, days passed, experience, coins, and tiles.
     */
    public Farmer() {
        this.farmerType = FarmerType.DEFAULT;
        this.days = 0;
        this.experience = 0;
        this.coins = 0;
        instantiateTiles();

        //Generate Rocks
        int numberOfRocks = FarmConstants.getRandomNumber(10, 30);

        int counter = 0;
        int randomRow;
        int randomColumn;
        do {
            randomRow = FarmConstants.getRandomNumber(0, FarmConstants.ROW - 1);
            randomColumn = FarmConstants.getRandomNumber(0, FarmConstants.COLUMN - 1);
            if (tile[randomRow][randomColumn].getStatus() != Tile.Status.HASROCKS) {
                tile[randomRow][randomColumn].setStatus(Tile.Status.HASROCKS);
                counter++;
            }
        } while (counter < numberOfRocks);
    }

    /**
     * Instantiate the tiles of the farmer.
     */
    private void instantiateTiles() {
        for (int i = 0; i < FarmConstants.ROW; i++) {
            for (int j = 0; j < FarmConstants.COLUMN; j++) {
                this.tile[i][j] = new Tile();
            }
        }
    }

    /**
     * A method that return the farmer type of the farmer.
     *
     * @return the number the farmer type of the farmer
     */
    public FarmerType getFarmerType() {
        return farmerType;
    }

    /**
     * Sets the farmer type for this object.
     *
     * @param farmerType the new farmer type to set
     */
    public void setFarmerType(FarmerType farmerType) {
        this.farmerType = farmerType;
    }

    /**
     * A method that returns the name of the farmer.
     *
     * @return the name of the farmer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the farmer.
     *
     * @param name the name of the farmer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method that return the days of the farmer.
     *
     * @return the number days of the farmer
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the number of days for this object.
     *
     * @param days the new number of days to set
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * A method that return the number of experience.
     *
     * @return the number of experience
     */
    public double getExperience() {
        return experience;
    }

    /**
     * Sets the experience for the farmer.
     *
     * @param experience the new experience to the farmer
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Adds experience to the farmer.
     *
     * @param experience the experience to add
     */
    public void addExperience(double experience) {
        this.experience += experience;
    }

    /**
     * A method that return the number coins.
     *
     * @return the coins.
     */
    public double getCoins() {
        return coins;
    }

    /**
     * Sets the number of coins owned by the farmer.
     *
     * @param coins the new number of coins to set
     */
    public void setCoins(double coins) {
        this.coins = coins;
    }

    /**
     * Adds coins to the farmer's total.
     *
     * @param coins the number of coins to add
     */
    public void addCoins(double coins) {
        this.coins += coins;
    }

    /**
     * Subtracts coins from the farmer's total.
     *
     * @param coins the number of coins to subtract
     */
    public void subtractCoins(double coins) {
        this.coins -= coins;
    }

    /**
     * Updates the farmer's type if the given type is higher than the current type.
     *
     * @param farmerType the new farmer type to set
     */
    public void updateFarmerType(FarmerType farmerType) {
        if (this.farmerType.ordinal() < farmerType.ordinal()) {
            this.farmerType = farmerType;
        } else {
            System.out.println("You don't have to demote yourself, please try again.");
        }
    }

    /**
     * Advances the game by one day, updating the state of the crops on the farm.
     */
    public void advanceToNextDay() {
        addDay();
        for (int i = 0; i < FarmConstants.ROW; i++) {
            for (int j = 0; j < FarmConstants.COLUMN; j++) {
                if (getTile()[i][j].getCrop() != null) {
                    getTile()[i][j].getCrop().addDaysGrown();
                    getTile()[i][j].getCrop().canHarvest();
                }
            }
        }
    }

    /**
     * Increments the number of days the farmer has spent on the farm.
     */
    public void addDay() {
        this.days++;
    }

    /**
     * A method that returns the specific tile.
     *
     * @return the specific tile.
     */
    public Tile[][] getTile() {
        return tile;
    }


}
