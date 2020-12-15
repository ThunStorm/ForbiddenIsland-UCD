package com.esr.service.game;

import com.esr.gui.game.GamePanel;
import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.DataListener;
import com.esr.gui.updater.LogAgent;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.utils.Audio;
import com.esr.utils.Constant;

import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class Game {
    private static int roundNum = 0;
    private static int actionCount = 0;
    private static int numOfPlayer;
    public GameData gameData;
    public UpdaterAgent updaterAgent;
    public DataListener dataListener;
    public Controllers controllers;


    public Game(int numOfPlayers, int waterLevel) {
        gameData = new GameData(numOfPlayers, waterLevel);
        updaterAgent = new UpdaterAgent();
        dataListener = new DataListener();
        controllers = new Controllers();
        numOfPlayer = numOfPlayers;

        LogAgent.logMessenger("Initialise Players...");
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

        LogAgent.logMessenger("Island starts to sink...");
//        UpdaterAgent.getFloodUpdater().guiUpdate(GameData.getFloodDeck().getNFlood(6));
        UpdaterAgent.getFloodUpdater().guiUpdate();
        GameData.getBoard().sinkTiles(GameData.getFloodDeck().getNFlood());
        GameData.getFloodDeck().Discard();
        GameData.getFloodDeck().setToNorm();

        LogAgent.logMessenger("[ Game Start ! ]");
        LogAgent.logMessenger("[ Player " + (roundNum + 1)  + " ]\n(" + GameData.getAdventurers()[roundNum].getName() + "'s Round)");
//        UpdaterAgent.getTreasureUpdater().guiUpdate(GameData.getTreasureDeck().getNTreasureCards(2));
        UpdaterAgent.getTreasureUpdater().guiUpdate();
        playerAudio();
    }

    public static void MainGame(){
        playerAudio();
        roundNum ++;
        roundNum = roundNum % numOfPlayer;
        actionCount = 0;
        LogAgent.logMessenger("[ Player " + (roundNum + 1)  + " ]\n(" + GameData.getAdventurers()[roundNum].getName() + "'s Round)");
    }

    public static void GameComplete(){
        System.out.println("Game Success");
    }

    public static int getNumOfPlayer(){ return numOfPlayer;}

    public static void doAction(){actionCount += 1;}

    public static int getActionCount(){return actionCount;}

    public static int getRoundNum() { return roundNum;}

    private static void playerAudio(){
        if (Constant.AUDIO_ON_OFF){
            switch (roundNum){
                case 0:
                    Audio.PLAYER1.Play();
                    break;
                case 1:
                    Audio.PLAYER2.Play();
                    break;
                case 2:
                    Audio.PLAYER3.Play();
                    break;
                case 3:
                    Audio.PLAYER4.Play();
                    break;
            }
        }
    }
}
