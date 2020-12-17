package com.esr.service.game;

import com.esr.gui.game.GamePanel;
import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.DataListener;
import com.esr.gui.updater.LogAgent;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.component.adventurer.Engineer;
import com.esr.service.game.data.Block;
import com.esr.utils.Audio;
import com.esr.utils.Constant;
import com.sun.xml.internal.bind.v2.TODO;

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
    private static boolean stage23Done = false;
    public GameData gameData;
    public UpdaterAgent updaterAgent;
    public DataListener dataListener;
    public Controllers controllers;


    public Game(int numOfPlayers, int waterLevel) {
        numOfPlayer = numOfPlayers;
        gameData = new GameData(numOfPlayers, waterLevel);
        dataListener = new DataListener();
        controllers = new Controllers();

        LogAgent.logMessenger("Initialise Players...");
        updaterAgent = new UpdaterAgent();

        LogAgent.logMessenger("Island starts to sink...");
        UpdaterAgent.getFloodUpdater().guiUpdate();
        GameData.getBoard().sinkTiles(GameData.getFloodDeck().getNFlood());
        GameData.getFloodDeck().Discard();
        GameData.getFloodDeck().setToNorm();

        LogAgent.logMessenger("[ Game Start ! ]");
        LogAgent.logMessenger("[ Player " + (roundNum + 1) + " ]\n(" + GameData.getAdventurers()[roundNum].getName() + "'s Round)");
        LogAgent.logMessenger("Please Take Up To 3 Actions");
        playerAudio();
    }

    public static void Stage23(){
        GameData.getDisplayedTreasureCard().addAll(GameData.getTreasureDeck().getNTreasureCards(2));
//        LogAgent.logMessenger(String.valueOf(GameData.getDisplayedTreasureCard()));
        UpdaterAgent.getTreasureUpdater().guiUpdate();

        //TODO deal with remove
        for (int i = 0; i < GameData.getDisplayedTreasureCard().size(); i++) {
            if (GameData.getDisplayedTreasureCard().get(i) == 25
                    || GameData.getDisplayedTreasureCard().get(i) == 26
                    || GameData.getDisplayedTreasureCard().get(i) == 27){
                GameData.getWaterMeter().WaterRise();
                GameData.getFloodDeck().PutBack2Top();
                GameData.getTreasureDeck().Discard(GameData.getDisplayedTreasureCard().get(i));
                GameData.getDisplayedTreasureCard().remove(i);
//                LogAgent.logMessenger("After discard " + String.valueOf(GameData.getDisplayedTreasureCard()));
            }
        }

        UpdaterAgent.getBoardUpdater().guiUpdate();
        UpdaterAgent.getTreasureUpdater().guiUpdate();
        UpdaterAgent.getPlayerUpdater().guiUpdate();
        UpdaterAgent.getWaterMeterUpdater().guiUpdate();

        UpdaterAgent.getFloodUpdater().guiUpdate();
        GameData.getBoard().sinkTiles(GameData.getFloodDeck().getNFlood());
        GameData.getFloodDeck().Discard();
        stage23Done = true;
    }

    public static void RoundEnd() {

        // Discard Stage
        if (GameData.getAdventurers()[roundNum].getHandCards().size() + GameData.getDisplayedTreasureCard().size() > 5) {
            LogAgent.logMessenger("You have more than 5 cards, please select all the cards you would like to discard and press [Discard]!");
            GameData.resetCardsInRound();
            return;
        }
        else {
            GameData.getAdventurers()[roundNum].getHandCards().addAll(GameData.getDisplayedTreasureCard());
            GameData.getDisplayedTreasureCard().clear();
            UpdaterAgent.getTreasureUpdater().guiUpdate();
            UpdaterAgent.getPlayerUpdater().guiUpdate();
        }

        // check win or lose
        if (GameData.getBoard().isShrinesFlooded()){
            GameComplete(false);
        }


//        GameData.SelectPawn(-1);
        if (GameData.getAdventurers()[roundNum] instanceof Engineer) {
            ((Engineer) GameData.getAdventurers()[roundNum]).resetShoreUpCount();
        }
        actionCount = 0;
        roundNum++;
        roundNum = roundNum % numOfPlayer;
        stage23Done = false;

        playerAudio();
        LogAgent.logMessenger("[ Player " + (roundNum + 1) + " ]\n(" + GameData.getAdventurers()[roundNum].getName() + "'s Round)");
    }

    public static void GameComplete(boolean isWin) {
//        TODO More Actions e.g. disable buttons.
        if (isWin){
            System.out.println("Game Success");
        }
        else{
            System.out.println("Game failed");
        }
    }

    public static int getNumOfPlayer() {
        return numOfPlayer;
    }

    public static void doAction() {
        actionCount += 1;
    }

    public static void moreAction() {
        actionCount -= 1;
    }

    public static int getActionCount() {
        return actionCount;
    }

    public static int getRoundNum() {
        return roundNum;
    }

    public static boolean isStage23Done() {
        return stage23Done;
    }


    private static void playerAudio() {
        if (Constant.AUDIO_ON_OFF) {
            switch (roundNum) {
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
