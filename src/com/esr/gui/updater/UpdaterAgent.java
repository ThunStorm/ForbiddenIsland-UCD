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

    public UpdaterAgent() {
        boardUpdater = new BoardUpdater();
        floodUpdater = new FloodUpdater();
        playerUpdater = new PlayerUpdater();
        treasureUpdater = new TreasureUpdater();
        waterMeterUpdater =new WaterMeterUpdater();
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

    public static WaterMeterUpdater getWaterMeterUpdater(){
        return waterMeterUpdater;
    }

    public int getUpdaterID(){ return 1;}
}
