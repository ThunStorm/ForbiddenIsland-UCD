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
    private BoardUpdater boardUpdater;
    private FloodUpdater floodUpdater;
    private PlayerUpdater playerUpdater;
    private TreasureUpdater treasureUpdater;

    public UpdaterAgent() {
        ArrayList<String> pawnImg = new ArrayList<>();
        for (Adventurer adventurer : GameData.getAdventurers()) {
            pawnImg.add(adventurer.getPawnImg());
        }
        this.boardUpdater = new BoardUpdater();
        this.floodUpdater = new FloodUpdater();
        this.playerUpdater = new PlayerUpdater(pawnImg);
        this.treasureUpdater = new TreasureUpdater();
    }

    public BoardUpdater getBoardUpdater() {
        return boardUpdater;
    }

    public FloodUpdater getFloodUpdater() {
        return floodUpdater;
    }

    public PlayerUpdater getPlayerUpdater() {
        return playerUpdater;
    }

    public TreasureUpdater getTreasureUpdater() {
        return treasureUpdater;
    }
}
