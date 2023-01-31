package com.delacruz.angelor.farmertype;

/**
 * Enumerates the different farmer types in the game.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public enum FarmerType {

    /**
     * Default farmer type.
     */
    DEFAULT("Default Farmer", 0, 0, 0, 0, 0, 0),

    /**
     * Registered farmer type.
     */
    REGISTERED("Registered Farmer", 5, 1, -1, 0, 0, 200),

    /**
     * Distinguished farmer type.
     */
    DISTINGUISHED("Distinguished Farmer", 10, 2, -2, 1, 0, 300),

    /**
     * Legendary farmer type.
     */
    LEGENDARY("Legendary Farmer", 15, 4, -3, 2, 1, 400);
    /**
     * The name of the farmer type.
     */

    private final String farmerTypeName;
    /**
     * The level requirement for this farmer type.
     */
    private final int levelRequirement;
    /**
     * The bonus earnings per produce for this farmer type.
     */
    private final int bonusEarningsPerProduce;
    /**
     * The seed cost reduction for this farmer type.
     */
    private final int seedCostReduction;
    /**
     * The water bonus limit increase for this farmer type.
     */
    private final int waterBonusLimitIncrease;
    /**
     * The fertilizer bonus limit increase for this farmer type.
     */
    private final int fertilizerBonusLimitIncrease;
    /**
     * The registration fee for this farmer type.
     */
    private final int registrationFee;

    /**
     * Constructs a new `FarmerType` with the given parameters.
     *
     * @param farmerTypeName               the name of the farmer type
     * @param levelRequirement             the level requirement for this farmer type
     * @param bonusEarningsPerProduce      the bonus earnings per produce for this farmer type
     * @param seedCostReduction            the seed cost reduction for this farmer type
     * @param waterBonusLimitIncrease      the water bonus limit increase for this farmer type
     * @param fertilizerBonusLimitIncrease the fertilizer bonus limit increase for this farmer type
     * @param registrationFee              the registration fee for this farmer type
     */
    FarmerType(String farmerTypeName, int levelRequirement, int bonusEarningsPerProduce, int seedCostReduction, int waterBonusLimitIncrease, int fertilizerBonusLimitIncrease, int registrationFee) {
        this.farmerTypeName = farmerTypeName;
        this.levelRequirement = levelRequirement;
        this.bonusEarningsPerProduce = bonusEarningsPerProduce;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusLimitIncrease = waterBonusLimitIncrease;
        this.fertilizerBonusLimitIncrease = fertilizerBonusLimitIncrease;
        this.registrationFee = registrationFee;
    }

    /**
     * Returns the name of the farmer type.
     *
     * @return the name of the farmer type
     */
    public String getFarmerTypeName() {
        return farmerTypeName;
    }

    /**
     * Returns the level requirement for this farmer type.
     *
     * @return the level requirement for this farmer type
     */
    public int getLevelRequirement() {
        return levelRequirement;
    }

    /**
     * Returns the bonus earnings per produce for this farmer type.
     *
     * @return the bonus earnings per produce for this farmer type
     */
    public int getBonusEarningsPerProduce() {
        return bonusEarningsPerProduce;
    }

    /**
     * Returns the seed cost reduction for this farmer type.
     *
     * @return the seed cost reduction for this farmer type
     */
    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    /**
     * Returns the water bonus limit increase for this farmer type.
     *
     * @return the water bonus limit increase for this farmer type
     */
    public int getWaterBonusLimitIncrease() {
        return waterBonusLimitIncrease;
    }

    /**
     * Returns the fertilizer bonus limit increase for this farmer type.
     *
     * @return the fertilizer bonus limit increase for this farmer type
     */
    public int getFertilizerBonusLimitIncrease() {
        return fertilizerBonusLimitIncrease;
    }

    /**
     * Returns the registration fee for this farmer type.
     *
     * @return the registration fee for this farmer type
     */
    public int getRegistrationFee() {
        return registrationFee;
    }
}
