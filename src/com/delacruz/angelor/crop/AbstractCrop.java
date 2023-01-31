package com.delacruz.angelor.crop;

/**
 * Abstract base class for crops in the game.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public abstract class AbstractCrop {

    /**
     * The name of the seed.
     */
    protected final String seedName;

    /**
     * The symbol representing the seed.
     */
    protected final String seedSymbol;

    /**
     * The type of crop that the seed produces.
     */
    protected final CropType cropType;

    /**
     * The number of days it takes for the crop to be ready for harvest.
     */
    protected final int harvestTimeInDays;

    /**
     * The water needs of the crop.
     */
    protected final int waterNeeds;

    /**
     * The fertilizer needs of the crop.
     */
    protected final int fertilizerNeeds;

    /**
     * The minimum number of products that can be produced by the crop.
     */
    protected final int minProductsProduced;

    /**
     * The maximum number of products that can be produced by the crop.
     */
    protected final int maxProductsProduced;

    /**
     * The actual number of products that will be produced by the crop.
     */
    protected final int productsProduced;

    /**
     * The base selling price per piece of the product.
     */
    protected final int baseSellingPricePerPiece;

    /**
     * The experience yield from harvesting the crop.
     */
    protected final double experienceYield;
    /**
     * The maximum number of times the crop can be watered for it to receive a bonus.
     */
    protected int waterBonusLimit;

    /**
     * The maximum number of times the crop can be fertilized for it to receive a bonus.
     */
    protected int fertilizerBonusLimit;

    /**
     * The cost of planting the seed for this crop.
     */
    protected int seedCost;

    /**
     * The number of days the crop has been grown.
     */
    protected int daysGrown;

    /**
     * The number of times the crop has been watered.
     */
    protected int timesCropWasWatered;

    /**
     * The number of times the crop has been fertilized.
     */
    protected int timesCropWasFertilized;

    /**
     * The base of the crops object by supplying the seed name, seed symbol, crop type,
     * harvest time in days, water needs, water bonus limit, fertilizer needs, fertilizer bonus limits,
     * products produced, seed cost, base selling price per piece, experience yield, timesCropWasWatered,
     * timesCropWasFertilized
     *
     * @param seedName                 the name of the seed
     * @param seedSymbol               the symbol of the seed
     * @param cropType                 the crop type of the seed
     * @param harvestTimeInDays        the harvest time in days of the crop
     * @param waterNeeds               the water needs of the crop
     * @param waterBonusLimit          the water bonus limit of the crop
     * @param fertilizerNeeds          the fertilizer needs of the crop
     * @param fertilizerBonusLimit     the fertilizer bonus limit of the crop
     * @param minProductsProduced      the minimum products produced of the crop
     * @param maxProductsProduced      the maximum products produced of the crop
     * @param productsProduced         the products produced of the crop
     * @param seedCost                 the seed cost of the seed
     * @param baseSellingPricePerPiece the base selling price per piece of the crop when harvested
     * @param experienceYield          the experience yield of the crop when harvested
     * @param daysGrown                the days grown of the crop
     * @param timesCropWasWatered      the number of times the crop was watered
     * @param timesCropWasFertilized   the number of times the crop was fertilized
     */
    public AbstractCrop(String seedName, String seedSymbol, CropType cropType, int harvestTimeInDays, int waterNeeds,
                        int waterBonusLimit, int fertilizerNeeds, int fertilizerBonusLimit, int minProductsProduced,
                        int maxProductsProduced, int productsProduced, int seedCost, int baseSellingPricePerPiece,
                        double experienceYield, int daysGrown, int timesCropWasWatered, int timesCropWasFertilized) {
        this.seedName = seedName;
        this.seedSymbol = seedSymbol;
        this.cropType = cropType;
        this.harvestTimeInDays = harvestTimeInDays;
        this.waterNeeds = waterNeeds;
        this.waterBonusLimit = waterBonusLimit;
        this.fertilizerNeeds = fertilizerNeeds;
        this.fertilizerBonusLimit = fertilizerBonusLimit;
        this.minProductsProduced = minProductsProduced;
        this.maxProductsProduced = maxProductsProduced;
        this.productsProduced = productsProduced;
        this.seedCost = seedCost;
        this.baseSellingPricePerPiece = baseSellingPricePerPiece;
        this.experienceYield = experienceYield;
        this.daysGrown = daysGrown;
        this.timesCropWasWatered = timesCropWasWatered;
        this.timesCropWasFertilized = timesCropWasFertilized;
    }

    /**
     * A method that returns the minimum products produced of the crop.
     *
     * @return the minimum products produced of the crop.
     */
    public int getMinProductsProduced() {
        return minProductsProduced;
    }

    /**
     * A method that returns the maximum products produced of the crop.
     *
     * @return the maximum products produced of the crop.
     */
    public int getMaxProductsProduced() {
        return maxProductsProduced;
    }

    /**
     * A method that returns the name of the seed.
     *
     * @return the name of the seed.
     */
    public String getSeedName() {
        return seedName;
    }

    /**
     * A method that returns the symbol of the seed.
     *
     * @return the symbol of the seed.
     */
    public String getSeedSymbol() {
        return seedSymbol;
    }

    /**
     * A method that returns the crop type of the seed.
     *
     * @return the crop type of the seed.
     */
    public CropType getCropType() {
        return cropType;
    }

    /**
     * A method that returns the harvest time in days of the crop
     * =
     *
     * @return the harvest time in days of the crop.
     */
    public int getHarvestTimeInDays() {
        return harvestTimeInDays;
    }

    /**
     * A method that returns the water needs of the crop.
     *
     * @return the water needs of the crop.
     */
    public int getWaterNeeds() {
        return waterNeeds;
    }

    /**
     * A method that returns the water bonus limit of the crop.
     *
     * @return the water bonus limit of the crop.
     */
    public int getWaterBonusLimit() {
        return waterBonusLimit;
    }

    /**
     * A method that sets the water bonus limit of the crop.
     *
     * @param waterBonusLimit the water bonus limit of the crop
     */
    public void setWaterBonusLimit(int waterBonusLimit) {
        this.waterBonusLimit = waterBonusLimit;
    }

    /**
     * A method that returns the fertilizer needs of the crop.
     *
     * @return the fertilizer needs of the crop.
     */
    public int getFertilizerNeeds() {
        return fertilizerNeeds;
    }

    /**
     * A method that returns the fertilizer bonus limit of the crop.
     *
     * @return the fertilizer bonus limit of the crop.
     */
    public int getFertilizerBonusLimit() {
        return fertilizerBonusLimit;
    }

    /**
     * A method that sets the fertilizer bonus limit of the crop.
     *
     * @param fertilizerBonusLimit the fertilizer bonus limit of the crop.
     */
    public void setFertilizerBonusLimit(int fertilizerBonusLimit) {
        this.fertilizerBonusLimit = fertilizerBonusLimit;
    }

    /**
     * A method that returns the products produced of the crop.
     *
     * @return the products produced of the crop.
     */
    public int getProductsProduced() {
        return productsProduced;
    }

    /**
     * A method that returns the seed cost of the seed.
     *
     * @return the seed cost of the seed.
     */
    public int getSeedCost() {
        return seedCost;
    }

    /**
     * A method that sets the seed cost of the seed.
     *
     * @param seedCost the seed cost of the seed.
     */
    public void setSeedCost(int seedCost) {
        this.seedCost = seedCost;
    }

    /**
     * A method that returns the base selling price per piece of the crop when harvested.
     *
     * @return the base selling price per piece of the crop when harvested.
     */
    public int getBaseSellingPricePerPiece() {
        return baseSellingPricePerPiece;
    }

    /**
     * A method that returns the experience yield of the crop when harvested.
     *
     * @return the experience yield of the crop when harvested.
     */
    public double getExperienceYield() {
        return experienceYield;
    }

    /**
     * A method that returns the days grown of the crop.
     *
     * @return the days grown of the crop.
     */
    public int getDaysGrown() {
        return daysGrown;
    }

    /**
     * A method that sets the days grown of the crop.
     *
     * @param daysGrown the days grown of the crop.
     */
    public void setDaysGrown(int daysGrown) {
        this.daysGrown = daysGrown;
    }

    /**
     * A method that return the number of times the crop was watered.
     *
     * @return the number of times the crop was watered.
     */
    public int getTimesCropWasWatered() {
        return timesCropWasWatered;
    }

    /**
     * A method that sets the number of times the crop was watered.
     *
     * @param timesCropWasWatered the number of times the crop was watered.
     */
    public void setTimesCropWasWatered(int timesCropWasWatered) {
        this.timesCropWasWatered = timesCropWasWatered;
    }

    /**
     * A method that return the number of times the crop was fertilized.
     *
     * @return the number of times the crop was fertilized.
     */
    public int getTimesCropWasFertilized() {
        return timesCropWasFertilized;
    }

    /**
     * A method that sets the number of times the crop was fertilized.
     *
     * @param timesCropWasFertilized the number of times the crop was fertilized.
     */
    public void setTimesCropWasFertilized(int timesCropWasFertilized) {
        this.timesCropWasFertilized = timesCropWasFertilized;
    }

    /**
     * A method that returns true if the crop is harvestable, otherwise, false.
     *
     * @return true if the crop is harvestable, otherwise, false.
     */
    public boolean canHarvest() {
        return daysGrown == harvestTimeInDays && !isWithered();
    }

    /**
     * A method that returns true if the crop is withered, otherwise, false.
     *
     * @return true if the crop is withered, otherwise, false.
     */
    public boolean isWithered() {
        return (timesCropWasWatered < waterNeeds || timesCropWasFertilized < fertilizerNeeds) && daysGrown >= harvestTimeInDays;
    }

    /**
     * A method that increments the times the crop was watered.
     */
    public void waterCrop() {
        timesCropWasWatered++;
    }

    /**
     * A method that increments the times the crop was fertilized.
     */
    public void fertilizeCrop() {
        timesCropWasFertilized++;
    }

    /**
     * A method that increments the days grown of the crop.
     */
    public void addDaysGrown() {
        daysGrown++;
    }

    /**
     * A method that adds the water bonus limit increase of the farmer.
     *
     * @param waterBonusLimitIncrease the water bonus limit increase of the farmer.
     */
    public void addWaterBonusLimit(int waterBonusLimitIncrease) {
        this.waterBonusLimit += waterBonusLimitIncrease;
    }

    /**
     * A method that reduce the water bonus limit increase of the farmer.
     *
     * @param waterBonusLimitIncrease the water bonus limit increase of the farmer.
     */
    public void reduceWaterBonusLimit(int waterBonusLimitIncrease) {
        this.waterBonusLimit -= waterBonusLimitIncrease;
    }

    /**
     * A method that adds the fertilizer bonus limit increase of the farmer.
     *
     * @param fertilizerBonusLimitIncrease the fertilizer bonus limit increase of the farmer.
     */
    public void addFertilizerBonusLimit(int fertilizerBonusLimitIncrease) {
        this.fertilizerBonusLimit += fertilizerBonusLimitIncrease;
    }

    /**
     * A method that reduce the fertilizer bonus limit increase of the farmer.
     *
     * @param fertilizerBonusLimitIncrease the fertilizer bonus limit increase of the farmer.
     */
    public void reduceFertilizerBonusLimit(int fertilizerBonusLimitIncrease) {
        this.fertilizerBonusLimit -= fertilizerBonusLimitIncrease;
    }

    /**
     * A method that adds the seed cost reduction of the farmer.
     *
     * @param seedCostReduction the seed cost reduction of the farmer.
     */
    public void addSeedCost(int seedCostReduction) {
        this.seedCost -= seedCostReduction;
    }

    /**
     * A method that reduce the seed cost reduction of the farmer.
     *
     * @param seedCostReduction the seed cost reduction of the farmer.
     */
    public void reduceSeedCost(int seedCostReduction) {
        this.seedCost += seedCostReduction;
    }
}
