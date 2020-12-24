package com.esr.gui.updater;

/**
 * @Description
 * @Author William
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class UpdaterAgent {
    private static BoardUpdater boardUpdater;
    private static FloodUpdater floodUpdater;
    private static PlayerUpdater playerUpdater;
    private static TreasureUpdater treasureUpdater;
    private static WaterMeterUpdater waterMeterUpdater;
    private static ControllersUpdater controllersUpdater;

    // a agent to update all gui in the game
    public UpdaterAgent() {
        boardUpdater = new BoardUpdater();
        floodUpdater = new FloodUpdater();
        playerUpdater = new PlayerUpdater();
        treasureUpdater = new TreasureUpdater();
        waterMeterUpdater = new WaterMeterUpdater();
        controllersUpdater = new ControllersUpdater();
    }

    public static BoardUpdater getBoardUpdater() {
        return boardUpdater;
    }

    public static FloodUpdater getFloodUpdater() {
        return floodUpdater;
    }

    public static PlayerUpdater getPlayerUpdater() {
        return playerUpdater;
    }

    public static TreasureUpdater getTreasureUpdater() {
        return treasureUpdater;
    }

    public static WaterMeterUpdater getWaterMeterUpdater() {
        return waterMeterUpdater;
    }

    public static ControllersUpdater getControllersUpdater() {
        return controllersUpdater;
    }

    public int getExplanation() {
        String reason2Keep = "I realize that this class could be a utility class and  can performed with keyword 'final'." +
                "To implement it, we have to do to much work to change usage of updater everywhere, so I will keep it here" +
                "and will optimize code in the future";
        return 1;
    }
}
