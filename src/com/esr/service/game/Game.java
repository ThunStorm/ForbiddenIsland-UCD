package com.esr.service.game;

import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.DataListener;
import com.esr.gui.updater.LogAgent;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.service.game.component.adventurer.Engineer;
import com.esr.utils.Audio;
import com.esr.utils.Constant;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class Game {
    private static int roundNum = 0;
    private static int fakeRoundNum = -1;
    private static int actionCount = 0;
    private static int numOfPlayer;
    private static boolean stage23Done = false;
    private static boolean need2save = false;
    private static ArrayList<Integer> playerIDinWater;
    public GameData gameData;
    public UpdaterAgent updaterAgent;
    public DataListener dataListener;
    public Controllers controllers;


    public Game(int numOfPlayers, int waterLevel) {
        numOfPlayer = numOfPlayers;
        playerIDinWater = new ArrayList<>();
        gameData = new GameData(numOfPlayers, waterLevel);
        dataListener = new DataListener();
        controllers = new Controllers();

        LogAgent.logMessenger("Initialise Players...");
        updaterAgent = new UpdaterAgent();

        LogAgent.logMessenger("Island starts to sink...");
//        if (Constant.AUDIO_ON_OFF){ Audio.FLOOD.Play(); }
        UpdaterAgent.getFloodUpdater().guiUpdate();
        GameData.getBoard().sinkTiles(GameData.getFloodDeck().getNFlood());
        GameData.getFloodDeck().Discard();
        GameData.getFloodDeck().setToNorm();

        LogAgent.logMessenger("[ Game Start ! ]");
        LogAgent.logMessenger("[ Player " + (roundNum + 1) + " ]\n(" + GameData.getAdventurers()[roundNum].getName() + "'s Round)");
        LogAgent.logMessenger("Please Take Up To 3 Actions");
        playerAudio();
    }

    public static void Stage23() {
        GameData.getDisplayedTreasureCard().addAll(GameData.getTreasureDeck().getNTreasureCards(2));
        actionCount = 3;
//        LogAgent.logMessenger(String.valueOf(GameData.getDisplayedTreasureCard()));
        UpdaterAgent.getTreasureUpdater().guiUpdate();


        Iterator<Integer> iterator = GameData.getDisplayedTreasureCard().iterator();
        while (iterator.hasNext()) {
            Integer treasureID = iterator.next();
            if (treasureID == 25 || treasureID == 26 || treasureID == 27) {
                GameData.getWaterMeter().WaterRise();
                GameData.getFloodDeck().PutBack2Top();
                GameData.getTreasureDeck().Discard(treasureID);
                iterator.remove();
            }
        }
        UpdaterAgent.getTreasureUpdater().guiUpdate();
        UpdaterAgent.getPlayerUpdater().guiUpdate();
        UpdaterAgent.getWaterMeterUpdater().guiUpdate();
        UpdaterAgent.getBoardUpdater().guiUpdate();

        UpdaterAgent.getFloodUpdater().guiUpdate();
        GameData.getBoard().sinkTiles(GameData.getFloodDeck().getNFlood());

        GameData.getFloodDeck().Discard();
        if (Constant.AUDIO_ON_OFF) {
            Audio.FLOOD.Play();
        }
        stage23Done = true;
    }

    public static void RoundEnd() {

        // Discard Stage
        if (GameData.getAdventurers()[roundNum].getHandCards().size() + GameData.getDisplayedTreasureCard().size() > 5) {
            LogAgent.logMessenger("You have more than 5 cards, please select all the cards you would like to discard and press [Discard]!");
            GameData.resetCardsInRound();
            return;
        } else {
            GameData.getAdventurers()[roundNum].getHandCards().addAll(GameData.getDisplayedTreasureCard());
            GameData.getDisplayedTreasureCard().clear();
            GameData.SelectPawn(-1);
            GameData.resetCardsInRound();
            UpdaterAgent.getTreasureUpdater().guiUpdate();
            UpdaterAgent.getPlayerUpdater().guiUpdate();
        }

        // check win or lose
        if (GameData.getBoard().isShrinesFlooded()) {
            LogAgent.logMessenger("Shrines and Treasures are sunk");
            GameComplete(false);
            return;
        }

        GameData.SelectPawn(-1);
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

    public static void SavePlayersRound() {

        if (playerIDinWater.size() == 0) {
            roundNum = fakeRoundNum;
            fakeRoundNum = -1;
            actionCount = 3;
            need2save = false;
            GameData.getBoard().sinkTiles(GameData.getFloodDeck().getNFlood());
            return;
        }

        for (Adventurer adventurer : GameData.getAdventurers()) {
            if (playerIDinWater.contains(adventurer.getId())) {
                roundNum = adventurer.getOrder();
                actionCount = 2;
                playerIDinWater.remove((Integer) adventurer.getId());

                int x = GameData.getAdventurers()[roundNum].getX();
                int y = GameData.getAdventurers()[roundNum].getY();
                if(x == 0){
                    if (!GameData.getBoard().getTile(x + 1, y).isExist() && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                    if (GameData.getAdventurers()[roundNum].getName().equals("Explorer") && !GameData.getBoard().getTile(x + 1, y).isExist()
                            && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()
                            && !GameData.getBoard().getTile(x + 1, y - 1).isExist() && !GameData.getBoard().getTile(x + 1, y + 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                }
                else if (x == 5){
                    if (!GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                    if (GameData.getAdventurers()[roundNum].getName().equals("Explorer")
                            && !GameData.getBoard().getTile(x - 1, y).isExist()
                            && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()
                            && !GameData.getBoard().getTile(x - 1, y - 1).isExist() && !GameData.getBoard().getTile(x - 1, y + 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                }
                else if (y == 0){
                    if (!GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                            && !GameData.getBoard().getTile(x, y + 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                    if (GameData.getAdventurers()[roundNum].getName().equals("Explorer")
                            && !GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                            && !GameData.getBoard().getTile(x, y + 1).isExist()
                            && !GameData.getBoard().getTile(x - 1, y + 1).isExist() && !GameData.getBoard().getTile(x + 1, y + 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                }
                else if (y == 5){
                    if (!GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                            && !GameData.getBoard().getTile(x, y - 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                    if (GameData.getAdventurers()[roundNum].getName().equals("Explorer")
                            && !GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                            && !GameData.getBoard().getTile(x, y - 1).isExist()
                            && !GameData.getBoard().getTile(x - 1, y - 1).isExist() && !GameData.getBoard().getTile(x + 1, y - 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                }
                else {
                    if (!GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                            && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                    if (GameData.getAdventurers()[roundNum].getName().equals("Explorer")
                            && !GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                            && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()
                            && !GameData.getBoard().getTile(x - 1, y - 1).isExist() && !GameData.getBoard().getTile(x + 1, y - 1).isExist()
                            && !GameData.getBoard().getTile(x - 1, y + 1).isExist() && !GameData.getBoard().getTile(x + 1, y + 1).isExist()) {
                        Game.GameComplete(false);
                        LogAgent.logMessenger("No adjacent tile to swim to");
                        return;
                    }
                }

                LogAgent.logMessenger("Select a nearest tile for [ Player " + (roundNum + 1) + " ] ("
                        + GameData.getAdventurers()[roundNum].getName() + ") to swim to and click [Move To]");
                break;
            }
        }
    }

    public static void GameComplete(boolean isWin) {
// TODO More Actions e.g. disable buttons.
        if (isWin) {
            System.out.println("Game Success");
            LogAgent.logMessenger("Game Success");
            if (Constant.AUDIO_ON_OFF) {
                Audio.WIN.Play();
            }
        } else {
            System.out.println("Game failed");
            LogAgent.logMessenger("Game failed");
            if (Constant.AUDIO_ON_OFF) {
                Audio.FAILURE.Play();
            }
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

    public static boolean isNeed2save() {
        return need2save;
    }

    public static void setNeed2save(boolean need2saveFlag) {
        need2save = need2saveFlag;
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


    public static void setFakeRoundNum(int fakeRoundNum) {
        Game.fakeRoundNum = fakeRoundNum;
    }

    public static void setPlayerIDinWater(ArrayList<Integer> playerIDinWater) {
        Game.playerIDinWater = playerIDinWater;
    }
}
