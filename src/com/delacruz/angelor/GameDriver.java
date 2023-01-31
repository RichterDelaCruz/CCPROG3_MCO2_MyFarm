package com.delacruz.angelor;

/** Angelo Richter L. Dela Cruz
 * S11
 */
import com.delacruz.angelor.constant.FarmConstants;
import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;
import com.delacruz.angelor.crop.impl.*;
import com.delacruz.angelor.farmertype.Farmer;
import com.delacruz.angelor.farmertype.FarmerType;

import java.util.Scanner;

public class GameDriver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Farmer farmer = new Farmer();

        System.out.print("Enter Your Name: ");
        farmer.setName(sc.nextLine());
        farmer.setCoins(10000);
        farmer.addExperience(100000);
        System.out.println("Your starting coin: " + farmer.getCoins());

        printFarm(farmer);
        String loopOne;
        do {
            // Presents the options for the user
            System.out.println("Enter [1]: Manage Farm");
            System.out.println("Enter [2]: Farmer Type Information");
            System.out.println("Enter [3]: Tool Information");
            System.out.println("Enter [4]: Seed Information");
            System.out.println("Enter [5]: Profile Information");
            System.out.println("Enter [6]: Advance To The Next Day");
            System.out.println("Enter [7]: Exit");

            loopOne = sc.nextLine();
            String loopTwo;
            switch (loopOne) {
                // When the user wants to manage the farm
                case "1":
                    printFarm(farmer);
                    // Prompts the user the coordinates of the tile to be managed
                    System.out.print("Input the row of the tile: ");
                    int inputRow = sc.nextInt();
                    System.out.print("Input the column of the tile: ");
                    int inputColumn = sc.nextInt();
                    sc.nextLine();
                    do {
                        printTileStatus(farmer, inputRow, inputColumn);
                        // Presents the options for the user whether to use tools, plant seed (shows only when the tile
                        // is plowed), and harvest (shows only when the crop is harvestable).
                        System.out.println("Enter [1]: Use Tools");
                        if (farmer.getTile()[inputRow][inputColumn].getCrop() == null && farmer.getTile()[inputRow][inputColumn].getStatus() == Tile.Status.PLOWED) {
                            System.out.println("Enter [2]: Plant Seed");
                        }
                        if (farmer.getTile()[inputRow][inputColumn].getCrop() != null && farmer.getTile()[inputRow][inputColumn].getCrop().canHarvest()) {
                            System.out.println("Enter [3]: Harvest");
                        }
                        System.out.println("Enter [B]: Back");
                        loopTwo = sc.nextLine();

                        switch (loopTwo) {
                            case "1":
                                useTools(farmer, inputRow, inputColumn);
                                break;
                            case "2":
                                if (farmer.getTile()[inputRow][inputColumn].getCrop() == null && farmer.getTile()[inputRow][inputColumn].getStatus() == Tile.Status.PLOWED) {
                                    plantSeeds(farmer, inputRow, inputColumn);
                                }
                                break;
                            case "3":
                                if (farmer.getTile()[inputRow][inputColumn].getCrop() != null && farmer.getTile()[inputRow][inputColumn].getCrop().canHarvest()) {
                                    harvestCrop(farmer, inputRow, inputColumn);
                                }
                                break;
                        }
                        printFarm(farmer);
                    } while (!loopTwo.equals("B"));
                    break;
                // When the user wants to show the information of the farmer types
                case "2":
                    printFarmerTypeInformation();
                    break;
                // When the user wants to show the information of the tools
                case "3":
                    printToolInformation();
                    break;
                // When the user wants to show the information of the seeds
                case "4":
                    printSeedInformation(farmer);
                    break;
                // When the user wants to see their profile
                case "5":
                    printProfileInformation(farmer);
                    break;
                // When the user wants to advance to the next day.
                case "6":
                    farmer.advanceToNextDay();
                    break;
            }
        } while (!loopOne.equals("7"));
    }

    /** A helper method that is in charge of displaying the farm like a tile board
     */
    private static void printFarm(Farmer farmer) {

        System.out.print("\t");
        for (int j = 0; j < FarmConstants.COLUMN; j++) {
            System.out.print(" " + j + " " + "\t");
        }
        System.out.println();

        for (int i = 0; i < FarmConstants.ROW; i++) {
            System.out.print(" " + i + " " + "\t");
            for (int j = 0; j < FarmConstants.COLUMN; j++) {
                if (farmer.getTile()[i][j].getCrop() != null) {
                    System.out.print(farmer.getTile()[i][j].getCrop().getSeedSymbol() + "\t");
                } else {
                    System.out.print(farmer.getTile()[i][j].getStatus().getStatusSymbol() + "\t");
                }
                if (j == FarmConstants.COLUMN - 1) {
                    System.out.println();
                }
            }
        }
    }

    /** A helper method that is in charge of displaying the status of the tile, and displays the status of the crop
     * if possible.
     *
     * @param farmer a farmer which has the tile board
     * @param inputRow the row of the tile that is being altered
     * @param inputColumn the column of the tile that is being altered
     */
    private static void printTileStatus(Farmer farmer, int inputRow, int inputColumn) {
        System.out.println("Status : " + farmer.getTile()[inputRow][inputColumn].getStatus());
        if (farmer.getTile()[inputRow][inputColumn].getCrop() != null) {
            System.out.println("Seed Name: " + farmer.getTile()[inputRow][inputColumn].getCrop().getSeedName());
            System.out.println("Crop Type: " + farmer.getTile()[inputRow][inputColumn].getCrop().getCropType().getName());

            if (!farmer.getTile()[inputRow][inputColumn].getCrop().isWithered()) {
                if (!farmer.getTile()[inputRow][inputColumn].getCrop().canHarvest()) {
                    System.out.println("Remaining Days To Be Harvest: " + (farmer.getTile()[inputRow][inputColumn].getCrop().getHarvestTimeInDays() - farmer.getTile()[inputRow][inputColumn].getCrop().getDaysGrown()));

                    if (farmer.getTile()[inputRow][inputColumn].getCrop().getWaterNeeds() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasWatered() < 0) {
                        System.out.println("Remaining Water Needs: 0");
                        if ((farmer.getTile()[inputRow][inputColumn].getCrop().getWaterBonusLimit() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasWatered()) < 0) {
                            System.out.println("Remaining Water Bonus: 0");
                        } else {
                            System.out.println("Remaining Water Bonus: " + (farmer.getTile()[inputRow][inputColumn].getCrop().getWaterBonusLimit() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasWatered()));
                        }
                    } else {
                        System.out.println("Remaining Water Needs: " + (farmer.getTile()[inputRow][inputColumn].getCrop().getWaterNeeds() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasWatered()));
                        System.out.println("Remaining Water Bonus: " + (farmer.getTile()[inputRow][inputColumn].getCrop().getWaterBonusLimit() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasWatered()));
                    }

                    if (farmer.getTile()[inputRow][inputColumn].getCrop().getFertilizerNeeds() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasFertilized() < 0) {
                        System.out.println("Remaining Fertilizer Needs: 0");
                        if ((farmer.getTile()[inputRow][inputColumn].getCrop().getFertilizerBonusLimit() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasFertilized()) < 0) {
                            System.out.println("Remaining Fertilizer Bonus: 0");
                        } else {
                            System.out.println("Remaining Fertilizer Bonus: " + (farmer.getTile()[inputRow][inputColumn].getCrop().getFertilizerBonusLimit() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasFertilized()));
                        }
                    } else {
                        System.out.println("Remaining Fertilizer Needs: " + (farmer.getTile()[inputRow][inputColumn].getCrop().getFertilizerNeeds() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasFertilized()));
                        System.out.println("Remaining Fertilizer Bonus: " + (farmer.getTile()[inputRow][inputColumn].getCrop().getFertilizerBonusLimit() - farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasFertilized()));
                    }
                    System.out.println("Your Experience: " + farmer.getExperience());
                    System.out.println("Your Coins: " + farmer.getCoins());
                } else {
                    System.out.println("You can harvest the plant.");
                }
            } else {
                System.out.println("The plant is withered, please remove it by using the shovel.");
            }
        }
    }

    /** A helper method that is in charge of displaying the tools and altering the tile.
     */
    private static void useTools(Farmer farmer, int inputRow, int inputColumn) {
        Scanner sc = new Scanner(System.in);

        String answer;
        do {
            System.out.println("Status : " + farmer.getTile()[inputRow][inputColumn].getStatus());

            System.out.println("Enter [1]: Plow (Free)");
            System.out.println("Enter [2]: Watering Can (Free)");
            System.out.println("Enter [3]: Fertilizer (10 Coins)");
            System.out.println("Enter [4]: Pickaxe (50 Coins)");
            System.out.println("Enter [5]: Shovel (7 Coins)");
            System.out.println("Enter [I]: Tools Information");
            System.out.println("Enter [B]: Back");

            answer = sc.nextLine();

            switch (answer) {
                case "1":
                    if (farmer.getTile()[inputRow][inputColumn].getStatus() == Tile.Status.UNPLOWED) {
                        farmer.getTile()[inputRow][inputColumn].setStatus(Tile.Status.PLOWED);
                        farmer.addExperience(ToolType.PLOW.getExperienceGainFromUse());

                        System.out.println(farmer.getTile()[inputRow][inputColumn].getStatus());
                        System.out.println("Your Experience: " + farmer.getExperience());
                        break;
                    } else {
                        System.out.println("The tile can't be plowed");
                    }
                    break;
                case "2":
                    if (farmer.getTile()[inputRow][inputColumn].getCrop() == null) {
                        System.out.println("There's no planted seed in the tile");
                    } else {
                        farmer.getTile()[inputRow][inputColumn].getCrop().waterCrop();

                        farmer.addExperience(ToolType.WATERINGCAN.getExperienceGainFromUse());
                        printTileStatus(farmer, inputRow, inputColumn);

                    }
                    break;
                case "3":
                    if (farmer.getCoins() < ToolType.FERTILIZER.getCostOfUsage()) {
                        System.out.println("You don't have enough coins");
                        break;
                    } else if (farmer.getTile()[inputRow][inputColumn].getCrop() == null) {
                        System.out.println("There's no planted seed in the tile");
                        break;
                    } else {
                        farmer.getTile()[inputRow][inputColumn].getCrop().fertilizeCrop();

                        farmer.addExperience(ToolType.FERTILIZER.getExperienceGainFromUse());
                        farmer.subtractCoins(ToolType.FERTILIZER.getCostOfUsage());
                        printTileStatus(farmer, inputRow, inputColumn);
                        break;
                    }
                case "4":
                    if (farmer.getCoins() < ToolType.PICKAXE.getCostOfUsage()) {
                        System.out.println("You don't have enough coins");
                        break;
                    } else if (farmer.getTile()[inputRow][inputColumn].getStatus() != Tile.Status.HASROCKS) {
                        System.out.println("This tile doesn't have rocks.");
                        break;
                    } else {
                        farmer.getTile()[inputRow][inputColumn].setStatus(Tile.Status.UNPLOWED);
                        farmer.addExperience(ToolType.PICKAXE.getExperienceGainFromUse());
                        farmer.subtractCoins(ToolType.PICKAXE.getCostOfUsage());

                        System.out.println("Your Experience: " + farmer.getExperience());
                        System.out.println("Your Coins: " + farmer.getCoins());
                        break;
                    }
                case "5":
                    if (farmer.getCoins() < ToolType.SHOVEL.getCostOfUsage()) {
                        System.out.println("You don't have enough coins");
                        break;
                    } else if (farmer.getTile()[inputRow][inputColumn].getStatus() == Tile.Status.UNPLOWED || farmer.getTile()[inputRow][inputColumn].getStatus() == Tile.Status.HASROCKS) {
                        farmer.addExperience(ToolType.SHOVEL.getExperienceGainFromUse());
                        farmer.subtractCoins(ToolType.SHOVEL.getCostOfUsage());

                        System.out.println("There's no changes in the tile, still you have used your coins.");
                        System.out.println("Your Experience: " + farmer.getExperience());
                        System.out.println("Your Coins: " + farmer.getCoins());
                        break;
                    } else if (farmer.getTile()[inputRow][inputColumn].getStatus() == Tile.Status.PLOWED) {
                        farmer.addExperience(ToolType.SHOVEL.getExperienceGainFromUse());
                        farmer.subtractCoins(ToolType.SHOVEL.getCostOfUsage());
                        farmer.getTile()[inputRow][inputColumn].setStatus(Tile.Status.UNPLOWED);
                        System.out.println("The tile is now unplowed.");

                        if (farmer.getTile()[inputRow][inputColumn].getCrop() != null) {
                            farmer.getTile()[inputRow][inputColumn].setCrop(null);
                            System.out.println("The crop has successfully removed");
                        }
                        System.out.println("Your Experience: " + farmer.getExperience());
                        System.out.println("Your Coins: " + farmer.getCoins());
                        break;
                        //removes plants, reverts to unplowed;
                    }
                    break;
                case "I":
                    printToolInformation();
                    break;
                case "B":
                    break;
            }
        } while (!answer.equals("B"));
    }

    /** A helper method that is in charge of displaying the information of the tools
     */
    private static void printToolInformation() {
        System.out.println("Tool Information");
        System.out.println(ToolType.PLOW.getToolName() + "[1]");
        System.out.println("Function: Converts an unplowed tile to a plowed tile. \n" + "Can only be performed on an unplowed tile.");
        System.out.println("Cost of Usage: " + ToolType.PLOW.getCostOfUsage());
        System.out.println("Experienced Gain from Use: " + ToolType.PLOW.getExperienceGainFromUse());
        System.out.println();
        System.out.println(ToolType.WATERINGCAN.getToolName() + "[2]");
        System.out.println("Function: Adds to the total number of tiles a tile/crop has been watered. \n" + "Can only be performed on a plowed tile with a crop.");
        System.out.println("Cost of Usage: " + ToolType.WATERINGCAN.getCostOfUsage());
        System.out.println("Experienced Gain from Use: " + ToolType.WATERINGCAN.getExperienceGainFromUse());
        System.out.println();
        System.out.println(ToolType.FERTILIZER.getToolName() + "[3]");
        System.out.println("Function: Adds to the total number of tiles a tile/crop has been applied with fertilizer. \n" + "Can only be performed on a plowed tile with a crop.");
        System.out.println("Cost of Usage: " + ToolType.FERTILIZER.getCostOfUsage());
        System.out.println("Experienced Gain from Use: " + ToolType.FERTILIZER.getExperienceGainFromUse());
        System.out.println();
        System.out.println(ToolType.PICKAXE.getToolName() + "[4]");
        System.out.println("Function: Removes a rock from a tile. \n" + "Can only be applied to tiles with a rock.");
        System.out.println("Cost of Usage: " + ToolType.PICKAXE.getCostOfUsage());
        System.out.println("Experienced Gain from Use: " + ToolType.PICKAXE.getExperienceGainFromUse());
        System.out.println();
        System.out.println(ToolType.SHOVEL.getToolName() + "[5]");
        System.out.println("Function: Removes a withered plant from a tile. \n" + "Can be used on any tile/crop with varying effects, as described above.");
        System.out.println("Cost of Usage: " + ToolType.SHOVEL.getCostOfUsage());
        System.out.println("Experienced Gain from Use: " + ToolType.SHOVEL.getExperienceGainFromUse());
        System.out.println();
    }

    /** A helper method that is in charge of planting a seed.
     */
    private static void plantSeeds(Farmer farmer, int inputRow, int inputColumn) {
        Scanner sc = new Scanner(System.in);

        String answer;
        do {
            System.out.println("Enter [T]: Turnip (Root Crop)");
            System.out.println("Enter [C]: Carrot (Root Crop)");
            System.out.println("Enter [P]: Potato (Root Crop)");
            System.out.println("Enter [R]: Rose (Flower)");
            System.out.println("Enter [U]: Turnips (Flower)");
            System.out.println("Enter [S]: Sunflower (Flower)");
            System.out.println("Enter [M]: Mango (Fruit tree)");
            System.out.println("Enter [A]: Apple (Fruit tree)");
            System.out.println("Enter [I]: Seeds Information");
            System.out.println("Enter [B]: Back");

            answer = sc.nextLine();

            AbstractCrop crop = null;
            switch (answer) {
                case "T":
                    crop = new Turnip();
                    break;
                case "C":
                    crop = new Carrot();
                    break;
                case "P":
                    crop = new Potato();
                    break;
                case "R":
                    crop = new Rose();
                    break;
                case "U":
                    crop = new Turnips();
                    break;
                case "S":
                    crop = new Sunflower();
                    break;
                case "M":
                    crop = new Mango();
                    break;
                case "A":
                    crop = new Apple();
                    break;
                case "I":
                    printSeedInformation(farmer);
                    break;
                case "B":
                    break;
            }
            if (crop != null && isEnoughCoins(farmer, crop) && isValidSpace(farmer, crop, inputRow, inputColumn)) {
                computeCrop(farmer, crop, inputRow, inputColumn);
                System.out.println("You successfully planted a/an " + farmer.getTile()[inputRow][inputColumn].getCrop().getSeedName());
                System.out.println("Seed Price: " + farmer.getTile()[inputRow][inputColumn].getCrop().getSeedCost());
                System.out.println("Your coin total is: " + farmer.getCoins());
            }
        } while (!answer.equals("B") && farmer.getTile()[inputRow][inputColumn].getCrop() == null);
    }

    /** A helper method that is in charge of displaying the information of all the seeds
     */
    private static void printSeedInformation(Farmer farmer) {
        AbstractCrop crop;
        crop = new Turnip();
        seedInformation(crop, farmer);

        crop = new Carrot();
        seedInformation(crop, farmer);

        crop = new Potato();
        seedInformation(crop, farmer);

        crop = new Rose();
        seedInformation(crop, farmer);

        crop = new Turnips();
        seedInformation(crop, farmer);

        crop = new Sunflower();
        seedInformation(crop, farmer);

        crop = new Mango();
        seedInformation(crop, farmer);

        crop = new Apple();
        seedInformation(crop, farmer);
    }

    /** A helper method that is in charge of displaying the information of a specific seed.
     */
    private static void seedInformation(AbstractCrop crop, Farmer farmer) {

        System.out.println("Seed Name:  " + crop.getSeedName());
        System.out.println("Crop Type:  " + crop.getCropType().getName());
        System.out.println("Harvest Time in Days:  " + crop.getHarvestTimeInDays());
        System.out.println("Water Needs (bonus limit): " + crop.getWaterNeeds() + "(" + (crop.getWaterBonusLimit() + farmer.getFarmerType().getWaterBonusLimitIncrease()) + ")");
        System.out.println("Fertilizer Needs (bonus limit): " + crop.getFertilizerNeeds() + "(" + (crop.getFertilizerNeeds() + farmer.getFarmerType().getFertilizerBonusLimitIncrease()) + ")");
        System.out.println("Products Produced: " + crop.getMinProductsProduced() + "-" + crop.getMaxProductsProduced());
        System.out.println("Seed Cost: " + (crop.getSeedCost() + farmer.getFarmerType().getSeedCostReduction()));
        System.out.println("Base Selling Price per Piece: " + (crop.getBaseSellingPricePerPiece() + farmer.getFarmerType().getBonusEarningsPerProduce()));
        System.out.println("Experience Yield:  " + crop.getExperienceYield());
        System.out.println();
    }

    /** A helper method that is in charge of checking if the farmer has enough coins
     */
    private static boolean isEnoughCoins(Farmer farmer, AbstractCrop crop) {
        return !(crop.getSeedCost() - farmer.getFarmerType().getSeedCostReduction() > farmer.getCoins());
    }

    /** A helper method that is in charge of checking if the seed will be planted within the farm and checks if the
     * fruit tree is in a proper place.
     */
    private static boolean isValidSpace(Farmer farmer, AbstractCrop crop, int inputRow, int inputColumn) {
        if (inputRow >= FarmConstants.ROW || inputColumn >= FarmConstants.COLUMN) {
            return false;
        }
        if (crop.getCropType().equals(CropType.FRUIT_TREE)) {
            if ((inputRow == FarmConstants.ROW || inputColumn == FarmConstants.COLUMN) || (inputColumn < 0 || inputRow < 0)) {
                System.out.println("Fruit trees can't be planted on the edges and outside the farm!");
                return false;
            } else if (!(farmer.getTile()[inputRow - 1][inputColumn].getCrop() == null && farmer.getTile()[inputRow + 1][inputColumn].getCrop() == null && farmer.getTile()[inputRow][inputColumn - 1].getCrop() == null && farmer.getTile()[inputRow][inputColumn + 1].getCrop() == null)) {
                System.out.println("Fruit trees should be planted away at least one (1) tile away from occupied tiles");
                return false;
            }
        }
        return true;
    }

    /** A helper method that is in charge of changing the crops information according to the farmer's type.
     */
    private static void computeCrop(Farmer farmer, AbstractCrop crop, int inputRow, int inputColumn) {
        crop.reduceSeedCost(farmer.getFarmerType().getSeedCostReduction());
        crop.addWaterBonusLimit(farmer.getFarmerType().getWaterBonusLimitIncrease());
        crop.addFertilizerBonusLimit(farmer.getFarmerType().getFertilizerBonusLimitIncrease());

        farmer.subtractCoins(crop.getSeedCost());
        farmer.getTile()[inputRow][inputColumn].setCrop(crop);
    }

    /** A helper method that is in charge of harvesting the crop.
     */
    private static void harvestCrop(Farmer farmer, int inputRow, int inputColumn) {
        int harvestTotal, timesCropWasWatered, timesCropAddedFertilizer;
        double waterBonus, fertilizerBonus, finalHarvestPrice;

        timesCropWasWatered = Math.min(farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasWatered(), farmer.getTile()[inputRow][inputColumn].getCrop().getWaterBonusLimit());

        timesCropAddedFertilizer = Math.min(farmer.getTile()[inputRow][inputColumn].getCrop().getTimesCropWasFertilized(), farmer.getTile()[inputRow][inputColumn].getCrop().getFertilizerBonusLimit());

        harvestTotal = farmer.getTile()[inputRow][inputColumn].getCrop().getProductsProduced() * (farmer.getTile()[inputRow][inputColumn].getCrop().getBaseSellingPricePerPiece() + farmer.getFarmerType().getBonusEarningsPerProduce());
        waterBonus = harvestTotal * 0.2 * (timesCropWasWatered - 1);
        fertilizerBonus = harvestTotal * 0.5 * timesCropAddedFertilizer;
        finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        if (farmer.getTile()[inputRow][inputColumn].getCrop().getCropType() == CropType.FLOWER) {
            finalHarvestPrice = finalHarvestPrice * 1.1;
        }

        System.out.println("You successfully harvested " + farmer.getTile()[inputRow][inputColumn].getCrop().getSeedName());
        System.out.printf("Final Harvest Price: %.2f\n", finalHarvestPrice);

        farmer.addCoins(finalHarvestPrice);
        System.out.println("Your updated coin total is: " + farmer.getCoins());

        farmer.getTile()[inputRow][inputColumn].setCrop(null);
        farmer.getTile()[inputRow][inputColumn].setStatus(Tile.Status.UNPLOWED);
    }

    /** A helper method that is in charge of displaying the profile information of the farmer.
     */
    private static void printProfileInformation(Farmer farmer) {
        int Level = (int) farmer.getExperience() / 100;

        System.out.println("Name: " + farmer.getName());
        System.out.println("Days Passed: " + farmer.getDays());
        System.out.println("Level: " + Level);
        System.out.println("Coins: " + farmer.getCoins());
        System.out.println("Farmer Type: " + farmer.getFarmerType().getFarmerTypeName());


        System.out.println();

        if (farmer.getFarmerType() != FarmerType.LEGENDARY) {
            upgradeFarmer(farmer);
        }
    }

    /** A helper method that is in charge of displaying the information of all farmer types.
     */
    private static void printFarmerTypeInformation() {
        Farmer farmerTemp = new Farmer();

        farmerTemp.setFarmerType(FarmerType.DEFAULT);
        farmerTypeInformation(farmerTemp);

        farmerTemp.setFarmerType(FarmerType.REGISTERED);
        farmerTypeInformation(farmerTemp);

        farmerTemp.setFarmerType(FarmerType.DISTINGUISHED);
        farmerTypeInformation(farmerTemp);

        farmerTemp.setFarmerType(FarmerType.LEGENDARY);
        farmerTypeInformation(farmerTemp);
    }

    /** A helper method that is in charge of displaying the information of specific farmer type.
     */
    private static void farmerTypeInformation(Farmer farmer) {
        System.out.println("Farmer Type:  " + farmer.getFarmerType().getFarmerTypeName());
        System.out.println("Level Requirement:  " + farmer.getFarmerType().getLevelRequirement());
        System.out.println("Bonus Earnings per Produce:  " + farmer.getFarmerType().getBonusEarningsPerProduce());
        System.out.println("Seed Cost Reduction:  " + farmer.getFarmerType().getSeedCostReduction());
        System.out.println("Water Bonus Limit Increase:  " + farmer.getFarmerType().getWaterBonusLimitIncrease());
        System.out.println("Fertilizer Bonus Limit Increase:  " + farmer.getFarmerType().getFertilizerBonusLimitIncrease());
        System.out.println("Registration Fee (coins):  " + farmer.getFarmerType().getRegistrationFee());
        System.out.println();
    }

    /** A helper method that is in charge of upgrading the farmer's type and checking the farmer's qualification
     */
    private static void upgradeFarmer(Farmer farmer) {
        Scanner sc = new Scanner(System.in);

        String answer;
        do {

            System.out.println("Enter [1]: Upgrade Farmer");
            System.out.println("Enter [2]: See Farmer Types Information");
            System.out.println("Enter [B]: Back");

            answer = sc.nextLine();

            switch (answer) {
                case "1":
                    String chosenFarmerType;
                    do {
                        if (farmer.getFarmerType().ordinal() < 1) {
                            System.out.println("Enter [R]: Registered Farmer (200 Coins)");
                        }
                        if (farmer.getFarmerType().ordinal() < 2) {
                            System.out.println("Enter [D]: Distinguished Farmer (300 Coins)");
                        }
                        if (farmer.getFarmerType().ordinal() < 3) {
                            System.out.println("Enter [L]: Legendary Farmer (400 Coins)");
                        }
                        System.out.println("Enter [B]: Back");

                        chosenFarmerType = sc.nextLine();

                        switch (chosenFarmerType) {
                            case "R":
                                if (farmer.getFarmerType().ordinal() < 1 && farmer.getCoins() > FarmerType.REGISTERED.getRegistrationFee() && ((int) farmer.getExperience() / 100) > FarmerType.REGISTERED.getLevelRequirement()) {
                                    revertBenefits(farmer);
                                    farmer.setFarmerType(FarmerType.REGISTERED);
                                    addBenefits(farmer);
                                    System.out.println("You have registered successfully as " + FarmerType.REGISTERED.getFarmerTypeName());
                                } else if (farmer.getCoins() < FarmerType.REGISTERED.getRegistrationFee()) {
                                    System.out.println("Not enough coins");
                                } else if (((int) farmer.getExperience() / 100) < FarmerType.REGISTERED.getLevelRequirement()) {
                                    System.out.println("Not enough level");
                                }
                                break;
                            case "D":
                                if (farmer.getFarmerType().ordinal() < 2 && farmer.getCoins() > FarmerType.DISTINGUISHED.getRegistrationFee() && ((int) farmer.getExperience() / 100) > FarmerType.DISTINGUISHED.getLevelRequirement()) {
                                    revertBenefits(farmer);
                                    farmer.setFarmerType(FarmerType.DISTINGUISHED);
                                    addBenefits(farmer);
                                    System.out.println("You have registered successfully as " + FarmerType.DISTINGUISHED.getFarmerTypeName());
                                } else if (farmer.getCoins() < FarmerType.DISTINGUISHED.getRegistrationFee()) {
                                    System.out.println("Not enough coins");
                                } else if (((int) farmer.getExperience() / 100) < FarmerType.DISTINGUISHED.getLevelRequirement()) {
                                    System.out.println("Not enough level");
                                }
                                break;
                            case "L":
                                if (farmer.getFarmerType().ordinal() < 3 && farmer.getCoins() > FarmerType.LEGENDARY.getRegistrationFee() && ((int) farmer.getExperience() / 100) > FarmerType.LEGENDARY.getLevelRequirement()) {
                                    revertBenefits(farmer);
                                    farmer.setFarmerType(FarmerType.LEGENDARY);
                                    addBenefits(farmer);
                                    System.out.println("You have registered successfully as " + FarmerType.LEGENDARY.getFarmerTypeName());
                                } else if (farmer.getCoins() < FarmerType.LEGENDARY.getRegistrationFee()) {
                                    System.out.println("Not enough coins");
                                } else if (((int) farmer.getExperience() / 100) < FarmerType.LEGENDARY.getLevelRequirement()) {
                                    System.out.println("Not enough level");
                                }
                                break;
                            case "B":
                                break;
                        }
                    } while (!(chosenFarmerType.equals("B") || farmer.getFarmerType() == FarmerType.LEGENDARY));
                    break;
                case "2":
                    printFarmerTypeInformation();
                    break;
                case "B":
                    break;
            }
        } while (!(answer.equals("B") || farmer.getFarmerType() == FarmerType.LEGENDARY));
    }

    /** A helper method that is in charge of reverting the benefits of the farmer, to its farm tiles.
     */
    private static void revertBenefits(Farmer farmer) {
        for (int i = 0; i < FarmConstants.ROW; i++) {
            for (int j = 0; j < FarmConstants.COLUMN; j++) {
                if (farmer.getTile()[i][j].getCrop() != null) {
                    farmer.getTile()[i][j].getCrop().reduceWaterBonusLimit(farmer.getFarmerType().getWaterBonusLimitIncrease());
                    farmer.getTile()[i][j].getCrop().reduceFertilizerBonusLimit(farmer.getFarmerType().getFertilizerBonusLimitIncrease());
                    farmer.getTile()[i][j].getCrop().addSeedCost(farmer.getFarmerType().getSeedCostReduction());
                }
            }
        }
    }

    /** A helper method that is in charge of adding the benefits of the farmer, to its farm tiles.
     */
    private static void addBenefits(Farmer farmer) {
        for (int i = 0; i < FarmConstants.ROW; i++) {
            for (int j = 0; j < FarmConstants.COLUMN; j++) {
                if (farmer.getTile()[i][j].getCrop() != null) {
                    farmer.getTile()[i][j].getCrop().addWaterBonusLimit(farmer.getFarmerType().getWaterBonusLimitIncrease());
                    farmer.getTile()[i][j].getCrop().addFertilizerBonusLimit(farmer.getFarmerType().getFertilizerBonusLimitIncrease());
                    farmer.getTile()[i][j].getCrop().reduceSeedCost(farmer.getFarmerType().getSeedCostReduction());
                }
            }
        }
    }

    /** A helper method that is in charge of advancing to the next day.
     */
/*    private static void advanceToNextDay(Farmer farmer) {
        farmer.addDay();
        for (int i = 0; i < FarmConstants.ROW; i++) {
            for (int j = 0; j < FarmConstants.COLUMN; j++) {
                if (farmer.getTile()[i][j].getCrop() != null) {
                    farmer.getTile()[i][j].getCrop().addDaysGrown();
                    farmer.getTile()[i][j].getCrop().canHarvest();
                }
            }
        }
    }*/
}


