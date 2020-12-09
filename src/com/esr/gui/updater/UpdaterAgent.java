package com.esr.gui.updater;

import com.esr.gui.game.TreasurePanel;
import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.Adventurer;

import java.util.ArrayList;

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

    public UpdaterAgent() {
        ArrayList<String> pawnImg = new ArrayList<>();
        for (Adventurer adventurer : GameData.getAdventurers()) {
            pawnImg.add(adventurer.getPawnImg());
        }
        boardUpdater = new BoardUpdater(GameData.getTilesArray());
        floodUpdater = new FloodUpdater();
        playerUpdater = new PlayerUpdater(pawnImg);
        treasureUpdater = new TreasureUpdater();
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

    public int getUpdaterID(){ return 1;}
}
