package com.delacruz.angelor;

import com.delacruz.angelor.constant.FarmConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.security.cert.CertPath;

/**
 * This class represents the initialization of panels and buttons for the GameController
 * Last Updated: December 10, 2022
 *
 * @author Angelo Richter L. Dela Cruz
 * @version 1
 */
public class GameView {

    private JFrame mainFrame;
    private JLabel greetingsLbl, farmerNameLbl, farmerDaysLbl, farmerLevelLbl, farmerCoinsLbl, farmerTypeLbl, toolsLbl,
            seedsLbl, upgradeFarmerCostLbl, upgradeFarmerLbl, feedbackLbl, tileStatsLbl, toolPricesLbl, seedPricesLbl;

    private JTextField greetingNameTf;
    private JButton nameSubmitBtn, advanceDayBtn, harvestBtn, plowBtn, wateringCanBtn, fertilizerBtn, pickaxeBtn, shovelBtn, turnipBtn, carrotBtn,
            potatoBtn, roseBtn, turnipsBtn, sunflowerBtn, mangoBtn, appleBtn, registeredBtn, distinguishedBtn, legendaryBtn;
    private JButton[][] tileBtn;
    private JTextArea employeeListTextArea;
    private JPanel panelEast, panelWest, panelOptions, panelName, panelTiles, panelFarmer, panelTools, panelSeeds, panelInformation,
            panelPrices, panelTileStats, panelUpgradeFarmer, panelFeedback;

    private String[] seeds = {"None", "Turnip", "Carrot", "Potato", "Rose", "Turnips", "Sunflower", "Mango", "Apple"};

    /**
     * The `GameView` class represents the user interface of the game.
     * It is responsible for creating and displaying the different elements
     * of the game, such as the farm grid, the farmer data, and the options
     * available to the user.
     */
    public GameView() {
        //Main Frame
        this.mainFrame = new JFrame("MyFarm");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout(8, 6));
        this.mainFrame.setBackground(new Color(0xFFFFFF));
        this.getMainFrame().getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GREEN));
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //The First Panel
        this.panelName = new JPanel();
        this.panelName.setBackground(new Color(0x987a59));
        this.panelName.setLayout(new FlowLayout(5));
        this.mainFrame.add(panelName, BorderLayout.CENTER);

        //The East Panel
        this.panelEast = new JPanel();
        this.panelEast.setBackground(new Color(0x92ccce));
        this.panelEast.setLayout(new GridLayout(3, 1, 10, 10));
        this.panelEast.setBounds(0, 0, 400, 400);
        this.panelEast.setBackground(new Color(0xedcc9a));

        this.panelFarmer = new JPanel();
        this.panelFarmer.setLayout(new GridLayout(5, 1, 10, 10));
        this.panelFarmer.setBounds(0, 0, 400, 400);
        this.panelFarmer.setBackground(new Color(0xedcc9a));
        this.panelEast.add(panelFarmer);

        this.panelUpgradeFarmer = new JPanel();
        this.panelUpgradeFarmer.setLayout(new GridLayout(4, 1, 10, 10));
        this.panelUpgradeFarmer.setBackground(new Color(0xedcc9a));
        this.panelEast.add(panelUpgradeFarmer);

        //South Panel
        this.panelFeedback = new JPanel();
        this.panelFeedback.setBackground(new Color(0x34444c));

        //West Panel
        this.panelWest = new JPanel();
        this.panelWest.setLayout(new BorderLayout(8, 6));
        this.panelWest.setBackground(new Color(0x92ccce));

        this.panelOptions = new JPanel();
        this.panelOptions.setLayout(new GridLayout(17, 1, 10, 10));
        this.panelOptions.setBackground(new Color(0x92ccce));
        this.panelWest.add(panelOptions, BorderLayout.WEST);

        this.panelInformation = new JPanel();
        this.panelInformation.setLayout(new BorderLayout(8, 6));
        this.panelInformation.setBackground(new Color(0x92ccce));
        this.panelWest.add(panelInformation, BorderLayout.EAST);

        this.panelPrices = new JPanel();
        this.panelPrices.setLayout(new GridLayout(3, 1, 10, 10));
        this.panelPrices.setBackground(new Color(0x92ccce));
        this.panelInformation.add(panelPrices, BorderLayout.NORTH);

        this.panelTileStats = new JPanel();
        this.panelTileStats.setLayout(new GridLayout(1, 1, 10, 10));
        this.panelTileStats.setBackground(new Color(0x92ccce));
        this.panelInformation.add(panelTileStats, BorderLayout.CENTER);

        this.panelTiles = new JPanel();
        this.panelTiles.setLayout(new GridLayout(10, 5, 5, 5));
        this.panelTiles.setBackground(new Color(0x6f5415));

        initializeGreetingElements();
        initializeFarmerData();
        initializeFarm();
        initializeAdvanceDay();
        initializeHarvest();
        initializeTools();
        initializeSeeds();
        initializePrices();
        initializeTileStats();
        initializeUpgradeFarmer();
        initializeFeedback();

        this.mainFrame.setVisible(true);
    }

    /**
     * Returns the main frame of the game view.
     *
     * @return The main frame of the game view.
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Initializes the greeting elements of the game view.
     */
    public void initializeGreetingElements() {

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Enter Your Username: ");

        this.greetingNameTf = new JTextField();
        this.greetingNameTf.setColumns(10);

        this.nameSubmitBtn = new JButton("Submit");

        this.greetingsLbl = new JLabel(" ");

        this.panelName.add(greetingsPromptLbl);
        this.panelName.add(greetingNameTf);
        this.panelName.add(nameSubmitBtn);
        this.panelName.add(greetingsLbl);
    }

    /**
     * Initializes the farmer data elements of the game view.
     */
    public void initializeFarmerData() {
        this.farmerNameLbl = new JLabel();

        this.farmerDaysLbl = new JLabel();

        this.farmerLevelLbl = new JLabel();

        this.farmerCoinsLbl = new JLabel();

        this.farmerTypeLbl = new JLabel();

        this.panelFarmer.add(farmerNameLbl);
        this.panelFarmer.add(farmerDaysLbl);
        this.panelFarmer.add(farmerLevelLbl);
        this.panelFarmer.add(farmerCoinsLbl);
        this.panelFarmer.add(farmerTypeLbl);
    }

    /**
     * Initializes the farm elements of the game view.
     */
    public void initializeFarm() {
        this.tileBtn = new JButton[10][5];

        for (int i = 0; i < FarmConstants.ROW; i++) {
            for (int j = 0; j < FarmConstants.COLUMN; j++) {
                this.tileBtn[i][j] = new JButton();
                this.tileBtn[i][j].setPreferredSize(new Dimension(100, 100));
                this.panelTiles.add(this.tileBtn[i][j]);
            }
        }
    }

    /**
     * Initializes the advance day button of the game view.
     */
    public void initializeAdvanceDay() {
        this.advanceDayBtn = new JButton();
        this.advanceDayBtn.setText("Advance Day");

        this.panelOptions.add(advanceDayBtn);
    }

    /**
     * Initializes the harvest button of the game view.
     */
    public void initializeHarvest() {
        this.harvestBtn = new JButton();
        this.harvestBtn.setText("Harvest");

        this.panelOptions.add(harvestBtn);
    }

    /**
     * Initializes the tools panel of the game view.
     */
    public void initializeTools() {
        this.panelTools = new JPanel();
        this.panelTools.setBorder(new LineBorder(Color.BLACK, 0));
        this.panelTools.setLayout(new GridLayout(6, 1, 10, 10));
        this.panelTools.setBounds(0, 0, 400, 400);
        this.panelTools.setBackground(Color.yellow);

        JLabel toolsLbl = new JLabel();
        toolsLbl.setText("Tools");

        this.plowBtn = new JButton();
        this.plowBtn.setText("Plow");

        this.wateringCanBtn = new JButton();
        this.wateringCanBtn.setText("Watering Can");

        this.fertilizerBtn = new JButton();
        this.fertilizerBtn.setText("Fertilizer");

        this.pickaxeBtn = new JButton();
        this.pickaxeBtn.setText("Pickaxe");

        this.shovelBtn = new JButton();
        this.shovelBtn.setText("Shovel");

        this.shovelBtn = new JButton();
        this.shovelBtn.setText("Shovel");

        this.panelOptions.add(toolsLbl);
        this.panelOptions.add(plowBtn);
        this.panelOptions.add(wateringCanBtn);
        this.panelOptions.add(fertilizerBtn);
        this.panelOptions.add(pickaxeBtn);
        this.panelOptions.add(shovelBtn);
    }

    /**
     * Initializes the seed panel with labels and buttons for each seed.
     */
    public void initializeSeeds() {
        this.panelSeeds = new JPanel();
        this.panelSeeds.setBorder(new LineBorder(Color.BLACK, 0));
        this.panelSeeds.setLayout(new GridLayout(9, 1, 10, 10));
        this.panelSeeds.setBounds(0, 0, 400, 400);
        this.panelSeeds.setBackground(Color.green);

        JLabel seedsLbl = new JLabel();
        seedsLbl.setText("Seeds");

        this.turnipBtn = new JButton();
        this.turnipBtn.setText("Turnip");

        this.carrotBtn = new JButton();
        this.carrotBtn.setText("Carrot");

        this.potatoBtn = new JButton();
        this.potatoBtn.setText("Potato");

        this.roseBtn = new JButton();
        this.roseBtn.setText("Rose");

        this.turnipsBtn = new JButton();
        this.turnipsBtn.setText("Turnips");

        this.sunflowerBtn = new JButton();
        this.sunflowerBtn.setText("Sunflower");

        this.mangoBtn = new JButton();
        this.mangoBtn.setText("Mango");

        this.appleBtn = new JButton();
        this.appleBtn.setText("Apple");

        this.panelOptions.add(seedsLbl);
        this.panelOptions.add(turnipBtn);
        this.panelOptions.add(carrotBtn);
        this.panelOptions.add(potatoBtn);
        this.panelOptions.add(roseBtn);
        this.panelOptions.add(turnipsBtn);
        this.panelOptions.add(sunflowerBtn);
        this.panelOptions.add(mangoBtn);
        this.panelOptions.add(appleBtn);
        //panelWest.add(panelSeeds, BorderLayout.SOUTH);

    }

    /**
     * Initializes and adds labels for tool prices, seed prices, and the cost to upgrade a farmer to a panel.
     */
    public void initializePrices() {
        this.toolPricesLbl = new JLabel();
        toolPricesLbl.setText("Tool Prices");

        this.seedPricesLbl = new JLabel();
        seedPricesLbl.setText("Seed Prices");

        this.upgradeFarmerCostLbl = new JLabel();
        upgradeFarmerCostLbl.setText("Upgrade Farmer Cost");

        this.panelPrices.add(toolPricesLbl);
        this.panelPrices.add(seedPricesLbl);
        this.panelPrices.add(upgradeFarmerCostLbl);
    }

    /**
     * Initializes and adds a label for tile stats to a panel.
     */
    public void initializeTileStats() {
        this.tileStatsLbl = new JLabel();
        this.tileStatsLbl.setText("Tile Stats");

        this.panelTileStats.add(tileStatsLbl);
    }

    /**
     * Initializes and adds labels and buttons for upgrading a farmer to a panel.
     */
    public void initializeUpgradeFarmer() {
        this.upgradeFarmerLbl = new JLabel();
        this.upgradeFarmerLbl.setText("Upgrade Farmer");

        this.registeredBtn = new JButton();
        this.registeredBtn.setText("Registered Farmer");

        this.distinguishedBtn = new JButton();
        this.distinguishedBtn.setText("Distinguished Farmer");

        this.legendaryBtn = new JButton();
        this.legendaryBtn.setText("Legendary Farmer");

        this.panelUpgradeFarmer.add(upgradeFarmerLbl);
        this.panelUpgradeFarmer.add(registeredBtn);
        this.panelUpgradeFarmer.add(distinguishedBtn);
        this.panelUpgradeFarmer.add(legendaryBtn);
    }

    /**
     * Initializes and adds a label for feedback to a panel.
     */
    public void initializeFeedback() {
        this.feedbackLbl = new JLabel();
        this.feedbackLbl.setText(" ");

        this.panelFeedback.add(feedbackLbl);
    }

    /**
     * Sets the main frame for the tile crops for the farmer.
     *
     * @param mainFrame the main frame to be set for the tile crops
     */
    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Sets the text of the feedback label.
     *
     * @param text The text to set as the feedback label.
     */
    public void setFeedbackLbl(String text) {
        removeFeedbackLbl();
        this.feedbackLbl.setText("<html><font color='red'>" + text + "</font></html>");
    }

    /**
     * Removes the feedback label by setting its text to a space.
     */
    public void removeFeedbackLbl() {
        this.feedbackLbl.setText(" ");
    }

    /**
     * Removes the tile stats label by setting its text to "Tile Stats".
     */
    public void removeTileStatsLbl() {
        this.tileStatsLbl.setText("Tile Stats");
    }

    /**
     * Adds tile stats to the tile stats label.
     *
     * @param tileRow                The row of the tile.
     * @param tileColumn             The column of the tile.
     * @param tileStatus             The status of the tile.
     * @param seed                   The seed planted on the tile.
     * @param seedType               The type of seed planted on the tile.
     * @param daysGrown              The number of days the seed has been grown on the tile.
     * @param harvestTimeInDays      The number of days it will take for the seed to be ready for harvest.
     * @param timesCropWasWatered    The number of times the crop on the tile has been watered.
     * @param timesCropWasFertilized The number of times the crop on the tile has been fertilized.
     * @param waterNeeds             The water needs of the crop on the tile.
     * @param waterBonusLimit        The water bonus limit of the crop on the tile.
     * @param fertilizerNeeds        The fertilizer needs of the crop on the tile.
     * @param fertilizerBonusLimit   The fertilizer bonus limit of the crop on the tile.
     */
    public void addTileStats(int tileRow, int tileColumn, String tileStatus, String seed, String seedType, int daysGrown,
                             int harvestTimeInDays,
                             int timesCropWasWatered, int timesCropWasFertilized, int waterNeeds, int waterBonusLimit, int fertilizerNeeds,
                             int fertilizerBonusLimit) {

        this.tileStatsLbl.setText("Tile Row: " + tileRow + "<br/>" +
                "Tile Column: " + tileColumn + "<br/>" +
                "Tile Status: " + tileStatus + "<br/>" +
                "Seed: " + seed + "<br/>" +
                "Seed Type: " + seedType + "<br/>" +
                "Days Grown: " + daysGrown + "<br/>" +
                "Harvest Time in Days: " + harvestTimeInDays + "<br/>" +
                "Times Crop was Watered: " + timesCropWasWatered + "<br/>" +
                "Times Crop was Fertilized: " + timesCropWasFertilized + "<br/>" +
                "Water Needs: " + waterNeeds + "<br/>" +
                "Water Bonus Limit: " + waterBonusLimit + "<br/>" +
                "Fertilizer Needs: " + fertilizerNeeds + "<br/>" +
                "Fertilizer Bonus Limit:" + fertilizerBonusLimit + "<br/>");
        finalizeTileStatsLbl();
    }

    /**
     * Sets the tile stats format to be readable like the html texts
     */
    public void finalizeTileStatsLbl() {
        this.tileStatsLbl.setText("<html>" + this.tileStatsLbl.getText() + "<html/>");
    }

    /**
     * Adds the specified crop status to the tile at the specified row and column in the farm.
     *
     * @param tileRow    The row of the tile to update.
     * @param tileColumn The column of the tile to update.
     * @param tileStatus The new status of the crop.
     */
    public void addTileStats(int tileRow, int tileColumn, String tileStatus) {
        this.tileStatsLbl.setText("Tile Row: " + tileRow + "<br/>" +
                "Tile Column: " + tileColumn + "<br/>" +
                "Tile Status: " + tileStatus + "<br/>");

        finalizeTileStatsLbl();
    }

    /**
     * Initializes the tileStatsLbl object by setting its text to a blank space.
     */
    public void initializeTileStatsLbl() {
        this.tileStatsLbl.setText(" ");
    }

    /**
     * Sets the text of the tileStatsLbl object to the specified text.
     *
     * @param text the new text for the tileStatsLbl object
     */
    public void setTileStatsLbl(String text) {
        this.tileStatsLbl.setText(text);
    }

    /**
     * Removes the tool prices information by setting the text of the toolPricesLbl object to a blank space.
     */
    public void removeToolPricesLbl() {
        this.toolPricesLbl.setText(" ");
    }

    /**
     * Adds the price information for a specific tool to the toolPricesLbl object.
     *
     * @param toolName the name of the tool
     * @param text     the price of the tool in coins
     */
    public void addToolPricesLbl(String toolName, String text) {
        this.toolPricesLbl.setText(this.toolPricesLbl.getText() + " <br/>" +
                toolName + " : " + text + " coins");
    }

    /**
     * Sets the tool prices format to be readable like the html texts
     */
    public void finalizeToolPricesLbl() {
        this.toolPricesLbl.setText("<html>" + this.toolPricesLbl.getText() + "<html/>");
    }

    /**
     * Removes the text from the seed prices label.
     */
    public void removeSeedPricesLbl() {
        this.seedPricesLbl.setText("Seed Prices");
    }

    /**
     * Adds the seed prices to the seed prices label.
     *
     * @param seedName The name of the seed.
     * @param seedType The type of the seed.
     * @param cost     The cost of the seed.
     */
    public void addSeedPricesLbl(String seedName, String seedType, int cost) {
        this.seedPricesLbl.setText(this.seedPricesLbl.getText() + " <br/>" + seedName + " (" + seedType + ") : "
                + cost + " coins");
    }

    /**
     * Sets the seed prices format to be readable like the html texts
     */
    public void finalizeSeedPricesLbl() {
        this.seedPricesLbl.setText("<html>" + this.seedPricesLbl.getText() + "<html/>");
    }

    /**
     * Adds the upgrade farmer prices to the upgrade farmer cost label.
     *
     * @param farmerType The type of farmer to upgrade to.
     * @param cost       The cost of upgrading to the specified farmer type.
     */
    public void addUpgradeFarmerPrices(String farmerType, int cost) {
        this.upgradeFarmerCostLbl.setText(this.upgradeFarmerCostLbl.getText() + "<br/>" + farmerType + ": "
                + cost + " coins");
    }

    /**
     * Sets the upgrade farmer format to be readable like the html texts
     */
    public void finalizeUpgradeFarmerPrices() {
        this.upgradeFarmerCostLbl.setText("<html>" + this.upgradeFarmerCostLbl.getText() + "<html/>");
    }

    /**
     * Sets the text for the tools label.
     *
     * @param text The text to set for the tools label.
     */
    public void setToolsLbl(String text) {
        this.toolsLbl.setText("Name: " + text);
    }

    /**
     * Sets the text for the farmer name label.
     *
     * @param text The text to set for the farmer name label.
     */
    public void setFarmerNameLbl(String text) {
        this.farmerNameLbl.setText("Name: " + text);
    }

    /**
     * Sets the text for the farmer days label.
     *
     * @param text The text to set for the farmer days label.
     */
    public void setFarmerDaysLbl(String text) {
        this.farmerDaysLbl.setText("Days Passed: " + text);
    }

    /**
     * Sets the text for the farmer level label.
     *
     * @param text The text to set for the farmer level label.
     */
    public void setFarmerLevelLbl(String text) {
        this.farmerLevelLbl.setText("Level: " + text);
    }

    /**
     * Sets the text for the farmer coins label.
     *
     * @param text The text to set for the farmer coins label.
     */
    public void setFarmerCoinsLbl(String text) {
        this.farmerCoinsLbl.setText("Coins: " + text);
    }

    /**
     * Sets the text for the farmer type label.
     *
     * @param text The text to set for the farmer type label.
     */
    public void setFarmerTypeLbl(String text) {
        this.farmerTypeLbl.setText("Farmer Type: " + text);
    }

    /**
     * Gets the name from the greeting name text field.
     *
     * @return The name entered in the greeting name text field.
     */
    public String getName() {
        return this.greetingNameTf.getText();
    }

    /**
     * Adds an action listener to the name submit button.
     *
     * @param actionListener the action listener to be added
     */
    public void setNameSubmitBtnListener(ActionListener actionListener) {
        this.nameSubmitBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the registered button.
     *
     * @param actionListener the action listener to be added
     */
    public void setRegisteredBtnListener(ActionListener actionListener) {
        this.registeredBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the distinguished button.
     *
     * @param actionListener the action listener to be added
     */
    public void setDistinguishedBtnListener(ActionListener actionListener) {
        this.distinguishedBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the legendary button.
     *
     * @param actionListener the action listener to be added
     */
    public void setLegendaryBtnListener(ActionListener actionListener) {
        this.legendaryBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the advance day button.
     *
     * @param actionListener the action listener to be added
     */
    public void setAdvanceDayBtnListener(ActionListener actionListener) {
        this.advanceDayBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the harvest button.
     *
     * @param actionListener the action listener to be added
     */
    public void setHarvestBtnListener(ActionListener actionListener) {
        this.harvestBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the tile button at the specified coordinates.
     *
     * @param i              the x-coordinate of the tile button
     * @param j              the y-coordinate of the tile button
     * @param actionListener the action listener to be added
     */
    public void setTileBtnListener(int i, int j, ActionListener actionListener) {
        this.tileBtn[i][j].addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the plow button.
     *
     * @param actionListener the action listener to be added
     */
    public void setPlowBtn(ActionListener actionListener) {
        this.plowBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the watering can button.
     *
     * @param actionListener the action listener to be added
     */
    public void setWateringCanBtn(ActionListener actionListener) {
        this.wateringCanBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the fertilizer button.
     *
     * @param actionListener the action listener to be added
     */
    public void setFertilizerBtn(ActionListener actionListener) {
        this.fertilizerBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the pickaxe button.
     *
     * @param actionListener the action listener to be added
     */
    public void setPickaxeBtn(ActionListener actionListener) {
        this.pickaxeBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the shovel button.
     *
     * @param actionListener the action listener to be added
     */
    public void setShovelBtn(ActionListener actionListener) {
        this.shovelBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the turnip button.
     *
     * @param actionListener the action listener to be added
     */
    public void setTurnipBtn(ActionListener actionListener) {
        this.turnipBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the carrot button.
     *
     * @param actionListener the action listener to be added
     */
    public void setCarrotBtn(ActionListener actionListener) {
        this.carrotBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the potato button.
     *
     * @param actionListener the action listener to be added
     */
    public void setPotatoBtn(ActionListener actionListener) {
        this.potatoBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the rose button.
     *
     * @param actionListener the action listener to be added
     */
    public void setRoseBtn(ActionListener actionListener) {
        this.roseBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the turnips button.
     *
     * @param actionListener the action listener to be added
     */
    public void setTurnipsBtn(ActionListener actionListener) {
        this.turnipsBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the sunflower button.
     *
     * @param actionListener the action listener to be added
     */
    public void setSunflowerBtn(ActionListener actionListener) {
        this.sunflowerBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the mango button.
     *
     * @param actionListener the action listener to be added
     */
    public void setMangoBtn(ActionListener actionListener) {
        this.mangoBtn.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the apple button.
     *
     * @param actionListener the action listener to be added
     */
    public void setAppleBtn(ActionListener actionListener) {
        this.appleBtn.addActionListener(actionListener);
    }

    /**
     * Returns the 2D array of tile buttons.
     *
     * @return the 2D array of tile buttons
     */
    public JButton[][] getTileBtn() {
        return tileBtn;
    }

    /**
     * Sets the text of the tile button at the specified coordinates.
     *
     * @param i    the x-coordinate of the tile button
     * @param j    the y-coordinate of the tile button
     * @param text the text to be set
     */
    public void setTileBtnText(int i, int j, String text) {
        this.tileBtn[i][j].setText(text);
    }

    /**
     * Sets the text of the greetings label.
     *
     * @param text the text to be set
     */
    public void setGreetingsLblText(String text) {
        this.greetingsLbl.setText(text);
    }

    /**
     * Returns the panel containing the name input field and submit button.
     *
     * @return the panel containing the name input field and submit button
     */
    public JPanel getPanelName() {
        return panelName;
    }

    /**
     * Sets the panel containing the name input field and submit button.
     *
     * @param panelName the panel to be set
     */
    public void setPanelName(JPanel panelName) {
        this.panelName = panelName;
    }

    /**
     * Returns the panel containing the tiles.
     *
     * @return the panel containing the tiles
     */
    public JPanel getPanelTiles() {
        return panelTiles;
    }

    /**
     * Sets the panel containing the tiles.
     *
     * @param panelTiles the panel to be set
     */
    public void setPanelTiles(JPanel panelTiles) {
        this.panelTiles = panelTiles;
    }

    /**
     * Returns the panel containing the farmer's information.
     *
     * @return the panel containing the farmer's information
     */
    public JPanel getPanelFarmer() {
        return panelFarmer;
    }

    /**
     * Sets the panel containing the farmer's information.
     *
     * @param panelFarmer the panel to be set
     */
    public void setPanelFarmer(JPanel panelFarmer) {
        this.panelFarmer = panelFarmer;
    }

    /**
     * Returns the panel containing the east panel.
     *
     * @return the panel containing the east panel
     */
    public JPanel getPanelEast() {
        return panelEast;
    }

    /**
     * Returns the panel containing the tools.
     *
     * @return the panel containing the tools
     */
    public JPanel getPanelTools() {
        return panelTools;
    }

    /**
     * Sets the panel containing the tools.
     *
     * @param panelTools the panel to be set
     */
    public void setPanelTools(JPanel panelTools) {
        this.panelTools = panelTools;
    }


    /**
     * Returns the panel containing the options.
     *
     * @return the panel containing the options
     */
    public JPanel getPanelOptions() {
        return panelOptions;
    }

    /**
     * Sets the panel containing the options.
     *
     * @param panelOptions the panel to be set
     */
    public void setPanelOptions(JPanel panelOptions) {
        this.panelOptions = panelOptions;
    }

    /**
     * Returns the panel containing the west panel.
     *
     * @return the panel containing the west panel
     */
    public JPanel getPanelWest() {
        return panelWest;
    }

    /**
     * Returns the panel containing the information.
     *
     * @return the panel containing the information
     */
    public JPanel getPanelInformation() {
        return panelInformation;
    }

    /**
     * Returns the panel containing the feedback.
     *
     * @return the panel containing the feedback
     */
    public JPanel getPanelFeedback() {
        return panelFeedback;
    }
}
