package com.delacruz.angelor;

import com.delacruz.angelor.constant.FarmConstants;
import com.delacruz.angelor.crop.AbstractCrop;
import com.delacruz.angelor.crop.CropType;
import com.delacruz.angelor.farmertype.Farmer;
import com.delacruz.angelor.farmertype.FarmerType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class runs the whole program.
 *
 * <p>This class is responsible for starting and controlling the execution of the game.
 * It contains the main method, which is the entry point of the program.
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class GameController {

    /**
     * The main method, which is the entry point of the program.
     *
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {
        GameModel gameModel = new GameModel();

        GameView gameView = new GameView();

        /**
         * Invoked when the name submit button is clicked.
         *
         * This method updates the game model and game view when the user enters their name and clicks the
         * submit button. It sets the farmer's name in the model and updates the game view to show the game
         * screen, with the game grid and other UI elements. It also sets up listeners for the individual
         * tiles in the game grid, so that the user can interact with them.
         *
         */
        gameView.setNameSubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameModel.getFarmer().getName() == null) {
                    gameModel.getFarmer().setName(gameView.getName());
                    gameView.getMainFrame().remove(gameView.getPanelName());
                    gameView.getMainFrame().add(gameView.getPanelTiles(), BorderLayout.CENTER);
                    gameView.getMainFrame().add(gameView.getPanelWest(), BorderLayout.WEST);
                    gameView.getMainFrame().add(gameView.getPanelEast(), BorderLayout.EAST);
                    gameView.getMainFrame().add(gameView.getPanelFeedback(), BorderLayout.SOUTH);

                    for (int i = 0; i < FarmConstants.ROW; i++) {
                        for (int j = 0; j < FarmConstants.COLUMN; j++) {
                            updateTile(gameView, gameModel, i, j);
                            gameView.setTileBtnListener(i, j, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    for (int i = 0; i < FarmConstants.ROW; i++) {
                                        for (int j = 0; j < FarmConstants.COLUMN; j++) {
                                            if (e.getSource() == gameView.getTileBtn()[i][j]) {
                                                gameModel.setTileRow(i);
                                                gameModel.setTileColumn(j);

                                                if (gameModel.getFarmer().getTile()[i][j].getCrop() == null) {
                                                    gameView.addTileStats(gameModel.getTileRow(), gameModel.getTileColumn(),
                                                            gameModel.getFarmer().getTile()[i][j].getStatus().getStatusName());
                                                } else {
                                                    updateTileStats(gameView, gameModel);
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }

                    gameView.addToolPricesLbl(ToolType.PLOW.getToolName(), Integer.toString(ToolType.PLOW.getCostOfUsage()));
                    gameView.addToolPricesLbl(ToolType.WATERINGCAN.getToolName(), Integer.toString(ToolType.WATERINGCAN.getCostOfUsage()));
                    gameView.addToolPricesLbl(ToolType.FERTILIZER.getToolName(), Integer.toString(ToolType.FERTILIZER.getCostOfUsage()));
                    gameView.addToolPricesLbl(ToolType.PICKAXE.getToolName(), Integer.toString(ToolType.PICKAXE.getCostOfUsage()));
                    gameView.addToolPricesLbl(ToolType.SHOVEL.getToolName(), Integer.toString(ToolType.SHOVEL.getCostOfUsage()));
                    gameView.finalizeToolPricesLbl();

                    updateSeedPrices(gameView, gameModel);

                    gameView.addUpgradeFarmerPrices(gameModel.getFarmerRegistered().getFarmerType().getFarmerTypeName(),
                            gameModel.getFarmerRegistered().getFarmerType().getRegistrationFee());
                    gameView.addUpgradeFarmerPrices(gameModel.getFarmerDistinguished().getFarmerType().getFarmerTypeName(),
                            gameModel.getFarmerDistinguished().getFarmerType().getRegistrationFee());
                    gameView.addUpgradeFarmerPrices(gameModel.getFarmerLegendary().getFarmerType().getFarmerTypeName(),
                            gameModel.getFarmerLegendary().getFarmerType().getRegistrationFee());
                    gameView.finalizeUpgradeFarmerPrices();


                    //Upgrade Farmer
                    gameView.setRegisteredBtnListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getFarmerType().ordinal() < 1) {
                                if (gameModel.getFarmer().getFarmerType().ordinal() < 1 &&
                                        gameModel.getFarmer().getCoins() > FarmerType.REGISTERED.getRegistrationFee() &&
                                        ((int) gameModel.getFarmer().getExperience() / 100) > FarmerType.REGISTERED.getLevelRequirement()) {
                                    revertBenefits(gameModel.getFarmer());
                                    gameModel.getFarmer().subtractCoins(FarmerType.REGISTERED.getRegistrationFee());
                                    gameModel.getFarmer().setFarmerType(FarmerType.REGISTERED);
                                    updateProfileInformation(gameView, gameModel);
                                    addBenefits(gameModel.getFarmer());
                                    try {
                                        updateTileStats(gameView, gameModel);
                                    } catch (Exception ex) {
                                    }
                                    gameView.setFeedbackLbl("You have registered successfully as " + FarmerType.REGISTERED.getFarmerTypeName());
                                } else if (gameModel.getFarmer().getCoins() < FarmerType.REGISTERED.getRegistrationFee()) {
                                    gameView.setFeedbackLbl("Not enough coins");
                                } else if (((int) gameModel.getFarmer().getExperience() / 100) < FarmerType.REGISTERED.getLevelRequirement()) {
                                    gameView.setFeedbackLbl("Not enough level");
                                }
                            }
                        }
                    });

                    gameView.setDistinguishedBtnListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getFarmerType().ordinal() < 2) {
                                if (gameModel.getFarmer().getFarmerType().ordinal() < 2 &&
                                        gameModel.getFarmer().getCoins() > FarmerType.DISTINGUISHED.getRegistrationFee() &&
                                        ((int) gameModel.getFarmer().getExperience() / 100) > FarmerType.DISTINGUISHED.getLevelRequirement()) {
                                    revertBenefits(gameModel.getFarmer());
                                    gameModel.getFarmer().subtractCoins(FarmerType.DISTINGUISHED.getRegistrationFee());
                                    gameModel.getFarmer().setFarmerType(FarmerType.DISTINGUISHED);
                                    updateProfileInformation(gameView, gameModel);
                                    addBenefits(gameModel.getFarmer());
                                    try {
                                        updateTileStats(gameView, gameModel);
                                    } catch (Exception ex) {
                                    }
                                    gameView.setFeedbackLbl("You have registered successfully as " + FarmerType.DISTINGUISHED.getFarmerTypeName());
                                } else if (gameModel.getFarmer().getCoins() < FarmerType.DISTINGUISHED.getRegistrationFee()) {
                                    gameView.setFeedbackLbl("Not enough coins");
                                } else if (((int) gameModel.getFarmer().getExperience() / 100) < FarmerType.DISTINGUISHED.getLevelRequirement()) {
                                    gameView.setFeedbackLbl("Not enough level");
                                }
                            }
                        }
                    });

                    gameView.setLegendaryBtnListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getFarmerType().ordinal() < 3) {
                                if (gameModel.getFarmer().getFarmerType().ordinal() < 3 &&
                                        gameModel.getFarmer().getCoins() > FarmerType.LEGENDARY.getRegistrationFee() &&
                                        ((int) gameModel.getFarmer().getExperience() / 100) > FarmerType.LEGENDARY.getLevelRequirement()) {
                                    revertBenefits(gameModel.getFarmer());
                                    gameModel.getFarmer().subtractCoins(FarmerType.LEGENDARY.getRegistrationFee());
                                    gameModel.getFarmer().setFarmerType(FarmerType.LEGENDARY);
                                    updateProfileInformation(gameView, gameModel);
                                    addBenefits(gameModel.getFarmer());
                                    try {
                                        updateTileStats(gameView, gameModel);
                                    } catch (Exception ex) {
                                    }
                                    gameView.setFeedbackLbl("You have registered successfully as " + FarmerType.LEGENDARY.getFarmerTypeName());
                                } else if (gameModel.getFarmer().getCoins() < FarmerType.LEGENDARY.getRegistrationFee()) {
                                    gameView.setFeedbackLbl("Not enough coins");
                                } else if (((int) gameModel.getFarmer().getExperience() / 100) < FarmerType.LEGENDARY.getLevelRequirement()) {
                                    gameView.setFeedbackLbl("Not enough level");
                                }
                            }
                        }
                    });

                    //Advance Day
                    gameView.setAdvanceDayBtnListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gameModel.getFarmer().advanceToNextDay();
                            updateProfileInformation(gameView, gameModel);
                            updateTileStats(gameView, gameModel);
                        }
                    });

                    //Harvest
                    gameView.setHarvestBtnListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() != null &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().canHarvest()) {
                                harvestCrop(gameView, gameModel.getFarmer(), gameModel.getTileRow(), gameModel.getTileColumn());
                                updateProfileInformation(gameView, gameModel);
                                updateTile(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                gameView.addTileStats(gameModel.getTileRow(), gameModel.getTileColumn(),
                                        gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus().getStatusName());
                            }
                            gameView.setFeedbackLbl("Can't be harvest at the moment");
                        }
                    });

                    //Tools
                    gameView.setPlowBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.UNPLOWED) {
                                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].setStatus(Tile.Status.PLOWED);
                                gameModel.getFarmer().addExperience(ToolType.PLOW.getExperienceGainFromUse());

                                gameView.setFeedbackLbl(gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus().getStatusName());

                                updateProfileInformation(gameView, gameModel);
                                updateTile(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());

                                gameView.setFeedbackLbl("Your Experience: " + gameModel.getFarmer().getExperience());
                            } else {
                                gameView.setFeedbackLbl("The tile can't be plowed");
                            }
                        }
                    });

                    gameView.setWateringCanBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                gameView.setFeedbackLbl("There's no planted seed in the tile");
                            } else {
                                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().waterCrop();
                                gameModel.getFarmer().addExperience(ToolType.WATERINGCAN.getExperienceGainFromUse());
                                updateProfileInformation(gameView, gameModel);
                                updateTileStats(gameView, gameModel);
                                gameView.setFeedbackLbl("Watered successfully!");
                            }
                        }
                    });

                    gameView.setFertilizerBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getCoins() < ToolType.FERTILIZER.getCostOfUsage()) {
                                gameView.setFeedbackLbl("You don't have enough coins");
                            } else if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                gameView.setFeedbackLbl("There's no planted seed in the tile");
                            } else {
                                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().fertilizeCrop();
                                gameModel.getFarmer().addExperience(ToolType.FERTILIZER.getExperienceGainFromUse());
                                gameModel.getFarmer().subtractCoins(ToolType.FERTILIZER.getCostOfUsage());
                                updateProfileInformation(gameView, gameModel);
                                updateTileStats(gameView, gameModel);
                                gameView.setFeedbackLbl("Fertilized successfully");
                            }
                        }
                    });

                    gameView.setPickaxeBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getCoins() < ToolType.PICKAXE.getCostOfUsage()) {
                                gameView.setFeedbackLbl("You don't have enough coins");
                            } else if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() != Tile.Status.HASROCKS) {
                                gameView.setFeedbackLbl("This tile doesn't have rocks.");
                            } else {
                                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].setStatus(Tile.Status.UNPLOWED);
                                gameModel.getFarmer().addExperience(ToolType.PICKAXE.getExperienceGainFromUse());
                                gameModel.getFarmer().subtractCoins(ToolType.PICKAXE.getCostOfUsage());

                                gameView.setFeedbackLbl("Status of the tile is now unplowed");
                                updateProfileInformation(gameView, gameModel);
                                updateTile(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                    gameView.addTileStats(gameModel.getTileRow(), gameModel.getTileColumn(),
                                            gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus().getStatusName());
                                } else {
                                    updateTileStats(gameView, gameModel);
                                }
                            }
                        }
                    });

                    gameView.setShovelBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getCoins() < ToolType.SHOVEL.getCostOfUsage()) {
                                gameView.setFeedbackLbl("You don't have enough coins");
                            } else if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.UNPLOWED
                                    || gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.HASROCKS) {
                                gameModel.getFarmer().addExperience(ToolType.SHOVEL.getExperienceGainFromUse());
                                gameModel.getFarmer().subtractCoins(ToolType.SHOVEL.getCostOfUsage());

                                gameView.setFeedbackLbl("There's no changes in the tile, still you have used your coins.");
                            } else if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED) {
                                gameModel.getFarmer().addExperience(ToolType.SHOVEL.getExperienceGainFromUse());
                                gameModel.getFarmer().subtractCoins(ToolType.SHOVEL.getCostOfUsage());
                                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].setStatus(Tile.Status.UNPLOWED);
                                gameView.setFeedbackLbl("The tile is now unplowed.");

                                if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() != null) {
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].setCrop(null);
                                    gameView.setFeedbackLbl("The crop has successfully removed");
                                }
                                updateProfileInformation(gameView, gameModel);
                                updateTile(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                    gameView.addTileStats(gameModel.getTileRow(), gameModel.getTileColumn(),
                                            gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus().getStatusName());
                                } else {
                                    updateTileStats(gameView, gameModel);
                                }
                                //removes plants, reverts to unplowed;
                            }
                        }
                    });


                    //Seeds
                    gameView.setTurnipBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                if (gameModel.getFarmer().getCoins() < gameModel.getTurnipCrop().getSeedCost()) {
                                    gameView.setFeedbackLbl("You don't have enough coins");
                                } else {
                                    computeCrop(gameModel, gameModel.getTurnipCrop());
                                    updateProfileInformation(gameView, gameModel);
                                    updateTileSeed(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                    updateTileStats(gameView, gameModel);
                                }
                            }
                        }
                    });

                    gameView.setCarrotBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                if (gameModel.getFarmer().getCoins() < gameModel.getCarrotCrop().getSeedCost()) {
                                    gameView.setFeedbackLbl("You don't have enough coins");
                                } else {
                                    computeCrop(gameModel, gameModel.getCarrotCrop());
                                    updateProfileInformation(gameView, gameModel);
                                    updateTileSeed(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                    updateTileStats(gameView, gameModel);
                                }
                            }

                        }
                    });

                    gameView.setPotatoBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                if (gameModel.getFarmer().getCoins() < gameModel.getPotatoCrop().getSeedCost()) {
                                    gameView.setFeedbackLbl("You don't have enough coins");
                                } else {
                                    computeCrop(gameModel, gameModel.getPotatoCrop());
                                    updateProfileInformation(gameView, gameModel);
                                    updateTileSeed(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                    updateTileStats(gameView, gameModel);
                                }
                            }
                        }
                    });

                    gameView.setRoseBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                if (gameModel.getFarmer().getCoins() < gameModel.getRoseCrop().getSeedCost()) {
                                    gameView.setFeedbackLbl("You don't have enough coins");
                                } else {
                                    computeCrop(gameModel, gameModel.getRoseCrop());
                                    updateProfileInformation(gameView, gameModel);
                                    updateTileSeed(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                    updateTileStats(gameView, gameModel);
                                }
                            }
                        }
                    });

                    gameView.setTurnipsBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                if (gameModel.getFarmer().getCoins() < gameModel.getTurnipsCrop().getSeedCost()) {
                                    gameView.setFeedbackLbl("You don't have enough coins");
                                } else {
                                    computeCrop(gameModel, gameModel.getTurnipsCrop());
                                    updateProfileInformation(gameView, gameModel);
                                    updateTileSeed(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                    updateTileStats(gameView, gameModel);
                                }
                            }
                        }
                    });

                    gameView.setSunflowerBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                if (gameModel.getFarmer().getCoins() < gameModel.getSunflowerCrop().getSeedCost()) {
                                    gameView.setFeedbackLbl("You don't have enough coins");
                                } else {
                                    computeCrop(gameModel, gameModel.getSunflowerCrop());
                                    updateProfileInformation(gameView, gameModel);
                                    updateTileSeed(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                    updateTileStats(gameView, gameModel);
                                }
                            }
                        }
                    });

                    gameView.setMangoBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                if (gameModel.getFarmer().getCoins() < gameModel.getMangoCrop().getSeedCost()) {
                                    gameView.setFeedbackLbl("You don't have enough coins");
                                } else if (!isValidSpace(gameModel, gameModel.getMangoCrop())) {
                                    gameView.setFeedbackLbl("Fruit trees should be planted away at least one (1) tile away from occupied tiles");
                                } else {
                                    computeCrop(gameModel, gameModel.getMangoCrop());
                                    updateProfileInformation(gameView, gameModel);
                                    updateTileSeed(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                    updateTileStats(gameView, gameModel);
                                }
                            }
                        }
                    });

                    gameView.setAppleBtn(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus() == Tile.Status.PLOWED &&
                                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop() == null) {
                                if (gameModel.getFarmer().getCoins() < gameModel.getAppleCrop().getSeedCost()) {
                                    gameView.setFeedbackLbl("You don't have enough coins");
                                } else if (!isValidSpace(gameModel, gameModel.getAppleCrop())) {
                                    gameView.setFeedbackLbl("Fruit trees should be planted away at least one (1) tile away from occupied tiles");
                                } else {
                                    computeCrop(gameModel, gameModel.getAppleCrop());
                                    updateProfileInformation(gameView, gameModel);
                                    updateTileSeed(gameView, gameModel, gameModel.getTileRow(), gameModel.getTileColumn());
                                    updateTileStats(gameView, gameModel);
                                }
                            }
                        }
                    });


                }
                gameView.setGreetingsLblText("Hello Farmer " + gameView.getName() + "!");

                updateProfileInformation(gameView, gameModel);

            }
        });
    }

    /**
     * Updates the tile at the specified position in the game view.
     *
     * @param gameView  The game view to update the tile in.
     * @param gameModel The game model containing the updated tile information.
     * @param i         The row index of the tile to update.
     * @param j         The column index of the tile to update.
     */
    private static void updateTile(GameView gameView, GameModel gameModel, int i, int j) {
        gameView.setTileBtnText(i, j, gameModel.getFarmer().getTile()[i][j].getStatus().getStatusSymbol());
    }

    /**
     * Updates the statistics displayed for a specific tile in the game.
     *
     * @param gameView  the game view object.
     * @param gameModel the game model object.
     */
    private static void updateTileStats(GameView gameView, GameModel gameModel) {
        gameView.addTileStats(gameModel.getTileRow(), gameModel.getTileColumn(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getStatus().getStatusName(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getSeedName(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getCropType().getName(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getDaysGrown(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getHarvestTimeInDays(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getTimesCropWasWatered(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getTimesCropWasFertilized(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getWaterNeeds(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getWaterBonusLimit(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getFertilizerNeeds(),
                gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].getCrop().getFertilizerBonusLimit()
        );
    }

    /**
     * Updates the seed prices in the game view.
     *
     * @param gameView  The game view to update the seed prices in.
     * @param gameModel The game model containing the updated seed price information.
     */
    private static void updateSeedPrices(GameView gameView, GameModel gameModel) {
        gameView.removeSeedPricesLbl();

        gameView.addSeedPricesLbl(gameModel.getTurnipCrop().getSeedName(), gameModel.getTurnipCrop().getCropType().getName(),
                (gameModel.getTurnipCrop().getSeedCost() + gameModel.getFarmer().getFarmerType().getSeedCostReduction()));

        gameView.addSeedPricesLbl(gameModel.getCarrotCrop().getSeedName(), gameModel.getCarrotCrop().getCropType().getName(),
                (gameModel.getCarrotCrop().getSeedCost() + gameModel.getFarmer().getFarmerType().getSeedCostReduction()));

        gameView.addSeedPricesLbl(gameModel.getPotatoCrop().getSeedName(), gameModel.getPotatoCrop().getCropType().getName(),
                (gameModel.getPotatoCrop().getSeedCost() + gameModel.getFarmer().getFarmerType().getSeedCostReduction()));

        gameView.addSeedPricesLbl(gameModel.getRoseCrop().getSeedName(), gameModel.getRoseCrop().getCropType().getName(),
                (gameModel.getRoseCrop().getSeedCost() + gameModel.getFarmer().getFarmerType().getSeedCostReduction()));

        gameView.addSeedPricesLbl(gameModel.getTurnipsCrop().getSeedName(), gameModel.getTurnipsCrop().getCropType().getName(),
                (gameModel.getTurnipsCrop().getSeedCost() + gameModel.getFarmer().getFarmerType().getSeedCostReduction()));

        gameView.addSeedPricesLbl(gameModel.getSunflowerCrop().getSeedName(), gameModel.getSunflowerCrop().getCropType().getName(),
                (gameModel.getSunflowerCrop().getSeedCost() + gameModel.getFarmer().getFarmerType().getSeedCostReduction()));

        gameView.addSeedPricesLbl(gameModel.getMangoCrop().getSeedName(), gameModel.getMangoCrop().getCropType().getName(),
                (gameModel.getMangoCrop().getSeedCost() + gameModel.getFarmer().getFarmerType().getSeedCostReduction()));

        gameView.addSeedPricesLbl(gameModel.getAppleCrop().getSeedName(), gameModel.getAppleCrop().getCropType().getName(),
                (gameModel.getAppleCrop().getSeedCost() + gameModel.getFarmer().getFarmerType().getSeedCostReduction()));

        gameView.finalizeSeedPricesLbl();
    }

    /**
     * Reverts the benefits applied to the farmer's crops.
     *
     * @param farmer The farmer whose crops to revert the benefits for.
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

    /**
     * Updates the profile information in the game view.
     *
     * @param gameView  The game view to update the profile information in.
     * @param gameModel The game model containing the updated profile information.
     */
    private static void updateProfileInformation(GameView gameView, GameModel gameModel) {
        gameView.setFarmerNameLbl(gameModel.getFarmer().getName());
        gameView.setFarmerDaysLbl(Integer.toString(gameModel.getFarmer().getDays()));
        gameView.setFarmerLevelLbl(Double.toString(gameModel.getFarmer().getExperience() / 100));
        gameView.setFarmerCoinsLbl(Double.toString(gameModel.getFarmer().getCoins()));
        gameView.setFarmerTypeLbl(gameModel.getFarmer().getFarmerType().getFarmerTypeName());
    }

    /**
     * Adds the benefits of the farmer to their farm tiles.
     *
     * @param farmer The farmer whose benefits to add to their farm tiles.
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

    /**
     * Harvests the crop on the specified tile and updates the farmer's coins.
     *
     * @param gameView    The game view to update the feedback label in.
     * @param farmer      The farmer whose crop to harvest.
     * @param inputRow    The row index of the tile to harvest the crop from.
     * @param inputColumn The column index of the tile to harvest the crop from.
     */
    private static void harvestCrop(GameView gameView, Farmer farmer, int inputRow, int inputColumn) {
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

        gameView.setFeedbackLbl("You successfully harvested " + farmer.getTile()[inputRow][inputColumn].getCrop().getSeedName());
        gameView.setFeedbackLbl("Final Harvest Price:" + String.format(".2f%", finalHarvestPrice));

        farmer.addCoins(finalHarvestPrice);
        gameView.setFeedbackLbl("Your updated coin total is: " + farmer.getCoins());

        farmer.getTile()[inputRow][inputColumn].setCrop(null);
        farmer.getTile()[inputRow][inputColumn].setStatus(Tile.Status.UNPLOWED);
    }

    /**
     * Computes the crop by applying the farmer's benefits to the seed cost, water and fertilizer bonus limits.
     * Also, subtracts the seed cost from the farmer's coins and sets the crop on the specified tile.
     *
     * @param gameModel The game model containing the farmer and tile information.
     * @param crop      The crop to compute and set on the tile.
     */
    private static void computeCrop(GameModel gameModel, AbstractCrop crop) {

        AbstractCrop tempCrop = crop;
        tempCrop.reduceSeedCost(gameModel.getFarmer().getFarmerType().getSeedCostReduction());
        tempCrop.addWaterBonusLimit(gameModel.getFarmer().getFarmerType().getWaterBonusLimitIncrease());
        tempCrop.addFertilizerBonusLimit(gameModel.getFarmer().getFarmerType().getFertilizerBonusLimitIncrease());
        gameModel.getFarmer().subtractCoins(tempCrop.getSeedCost());
        gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn()].setCrop(tempCrop);
    }

    /**
     * Updates the tile seed symbol in the game view using the seed symbol of the crop on the tile.
     *
     * @param gameView  The game view to update the tile seed symbol in.
     * @param gameModel The game model containing the farmer and tile information.
     * @param i         The row index of the tile to update the seed symbol for.
     * @param j         The column index of the tile to update the seed symbol for.
     */
    private static void updateTileSeed(GameView gameView, GameModel gameModel, int i, int j) {
        gameView.setTileBtnText(i, j, gameModel.getFarmer().getTile()[i][j].getCrop().getSeedSymbol());
    }

    /**
     * Checks if the specified tile is a valid space to plant the given crop.
     * <p>
     * For fruit trees, the surrounding tiles must be empty in order for the space to be valid.
     *
     * @param gameModel The game model containing the farmer and tile information.
     * @param crop      The crop to check if the tile is a valid space for.
     * @return true if the tile is a valid space to plant the crop, false otherwise.
     */
    private static boolean isValidSpace(GameModel gameModel, AbstractCrop crop) {
        if (crop.getCropType().equals(CropType.FRUIT_TREE)) {
            if (!(gameModel.getFarmer().getTile()[gameModel.getTileRow() - 1][gameModel.getTileColumn()].getCrop() == null &&
                    gameModel.getFarmer().getTile()[gameModel.getTileRow() + 1][gameModel.getTileColumn()].getCrop() == null &&
                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn() - 1].getCrop() == null &&
                    gameModel.getFarmer().getTile()[gameModel.getTileRow()][gameModel.getTileColumn() + 1].getCrop() == null)) {
                return false;
            }
            return true;
        }
        return true;
    }

}
