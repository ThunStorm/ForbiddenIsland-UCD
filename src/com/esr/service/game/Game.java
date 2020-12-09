package com.esr.service.game;

import com.esr.gui.game.GamePanel;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.component.adventurer.Adventurer;

import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class Game {
    public GameData gameData;

    public Game(int numOfPlayers, int waterLevel) {
        gameData = new GameData(numOfPlayers, waterLevel);
        UpdaterAgent updaterAgent = new UpdaterAgent();

        if (numOfPlayers == 4){
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p1HandCards,  GameData.getAdventurers()[0].getHandCards());
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p2HandCards,  GameData.getAdventurers()[1].getHandCards());
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p3HandCards,  GameData.getAdventurers()[2].getHandCards());
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p4HandCards,  GameData.getAdventurers()[3].getHandCards());
        }
        else if (numOfPlayers == 3){
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p1HandCards,  GameData.getAdventurers()[0].getHandCards());
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p2HandCards,  GameData.getAdventurers()[1].getHandCards());
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p3HandCards,  GameData.getAdventurers()[2].getHandCards());
        }
        else {
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p1HandCards,  GameData.getAdventurers()[0].getHandCards());
            UpdaterAgent.getPlayerUpdater().handCardUpdater(GamePanel.p2HandCards,  GameData.getAdventurers()[1].getHandCards());
        }

        UpdaterAgent.getTreasureUpdater().guiUpdate(GameData.getTreasureDeck().getNTreasureCards(2));
        UpdaterAgent.getFloodUpdater().guiUpdate(GameData.getFloodDeck().getNFlood(6));
////        After operations
//        GameData.getFloodDeck().Discard();

    }

}
