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
    private GameData gameData;

    public Game(int numOfPlayers, int waterLevel) {
        gameData = new GameData(numOfPlayers, waterLevel);
        UpdaterAgent updaterAgent = new UpdaterAgent();
        if (numOfPlayers == 4){
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p1HandCards,  GameData.getAdventurers()[0].getHandCards());
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p2HandCards,  GameData.getAdventurers()[1].getHandCards());
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p3HandCards,  GameData.getAdventurers()[2].getHandCards());
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p4HandCards,  GameData.getAdventurers()[3].getHandCards());
        }
        else if (numOfPlayers == 3){
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p1HandCards,  GameData.getAdventurers()[0].getHandCards());
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p2HandCards,  GameData.getAdventurers()[1].getHandCards());
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p3HandCards,  GameData.getAdventurers()[2].getHandCards());
        }
        else {
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p1HandCards,  GameData.getAdventurers()[0].getHandCards());
            updaterAgent.getPlayerUpdater().handCardUpdate(GamePanel.p2HandCards,  GameData.getAdventurers()[1].getHandCards());
        }

    }

}
