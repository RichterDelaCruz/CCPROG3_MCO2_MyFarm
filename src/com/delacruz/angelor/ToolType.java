package com.delacruz.angelor;

/**
 * This documents "Tools"
 * <p>
 * Last Updated: December 10, 2022
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public enum ToolType {
    /**
     * Plow tool type.
     */
    PLOW("Plow", 0, 0.5),

    /**
     * Watering can tool type.
     */
    WATERINGCAN("Watering Can", 0, 0.5),

    /**
     * Fertilizer tool type.
     */
    FERTILIZER("Fertilizer", 10, 4),

    /**
     * Pickaxe tool type.
     */
    PICKAXE("Pickaxe", 50, 15),

    /**
     * Shovel tool type.
     */
    SHOVEL("Shovel", 7, 2);

    private final String toolName;
    private final int costOfUsage;
    private final double experienceGainFromUse;

    /**
     * The tool type object is in charge of supplying the name of the tool, the cost of usage of the tool, the
     * experience gain from use of the tool
     *
     * @param toolName              the name of the tool
     * @param costOfUsage           the cost of usage of the tool
     * @param experienceGainFromUse the experience gain from use of the tool
     */
    ToolType(String toolName, int costOfUsage, double experienceGainFromUse) {
        this.toolName = toolName;
        this.costOfUsage = costOfUsage;
        this.experienceGainFromUse = experienceGainFromUse;
    }

    /**
     * Gets the cost of using this tool.
     *
     * @return the cost of usage
     */
    public int getCostOfUsage() {
        return costOfUsage;
    }

    /**
     * Gets the name of this tool.
     *
     * @return the tool name
     */
    public String getToolName() {
        return toolName;
    }

    /**
     * Gets the experience gain from using this tool.
     *
     * @return the experience gain from use
     */
    public double getExperienceGainFromUse() {
        return experienceGainFromUse;
    }
}
