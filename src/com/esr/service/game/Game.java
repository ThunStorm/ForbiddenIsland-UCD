package com.esr.service.game;

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
    // main game implementation
    private static int roundNum = 0;
    private static int fakeRoundNum = -1;
    private static int actionCount = 0;
    private static int fakeActionCount = 0;
    private static int numOfPlayer;
    private static boolean stage23Done = false;
    private static boolean need2save = false;
    private static boolean inFakeRound = false;
    private static ArrayList<Integer> playerIDinWater;
    public GameData gameData;

    // init main game with preset customized settings
    public Game(int numOfPlayers, int waterLevel) {
        numOfPlayer = numOfPlayers;
        playerIDinWater = new ArrayList<>();
        gameData = new GameData(numOfPlayers, waterLevel);
        LogAgent.logMessenger("Initialise Players...");

        // set up a updater agent for later update process
        new UpdaterAgent();

        LogAgent.logMessenger("Island starts to sink...");
//        if (Constant.AUDIO_ON_OFF){ Audio.FLOOD.Play(); }
        UpdaterAgent.getFloodUpdater().guiUpdate();
        GameData.getBoard().sinkTiles(GameData.getFloodDeck().getNCards());
        GameData.getFloodDeck().Discard();
        GameData.getFloodDeck().set2Norm();

        LogAgent.logMessenger("[ Game Start ! ]");
        LogAgent.logMessenger("[ Player " + (roundNum + 1) + " ]\n(" + GameData.getAdventurers()[roundNum].getName() + "'s Round)");
        LogAgent.logMessenger("Please Take Up To 3 Actions");
        playerAudio();
    }

    // enter in stage 2 and stage 3 : get 2 treasure and get flood cards to sink the tiles
    public static void Stage23() {
        GameData.getDisplayedTreasureCard().addAll(GameData.getTreasureDeck().getNCards());
        actionCount = 3;
//        LogAgent.logMessenger(String.valueOf(GameData.getDisplayedTreasureCard()));
        UpdaterAgent.getTreasureUpdater().guiUpdate();

        // try to avoid removing in loop where cursor will not updated and that caused ConcurrentModificationException
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
        GameData.getBoard().sinkTiles(GameData.getFloodDeck().getNCards());

        GameData.getFloodDeck().Discard();
        if (Constant.AUDIO_ON_OFF) {
            Audio.FLOOD.Play();
        }

        // set a flag that only all enter in stage 2 and 3 once
        stage23Done = true;
    }

    // operations after a round
    public static void RoundEnd() {
        // Discard Stage
        if (GameData.getAdventurers()[roundNum].getHandCards().size() + GameData.getDisplayedTreasureCard().size() > 5) {
            LogAgent.logMessenger("You Have More Than 5 Cards, Please Discard First!");
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
            LogAgent.logMessenger("[!] Shrines And Treasures Are Sunk");
            GameComplete(false);
            return;
        }

        // reset
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
        UpdaterAgent.getPlayerUpdater().guiUpdate();
    }

    // if someone falls into water, the method will call to save players to allow them move to a nearest tile
    public static void SavePlayersRound() {
        // check if all victims are saved
        if (playerIDinWater.size() == 0) {
            roundNum = fakeRoundNum;
            fakeRoundNum = -1;
            actionCount = 3;
            need2save = false;
            inFakeRound = false;
            UpdaterAgent.getControllersUpdater().guiUpdate();
            return;
        }

        // save victims
        for (Adventurer adventurer : GameData.getAdventurers()) {
            if (playerIDinWater.contains(adventurer.getId())) {
                roundNum = adventurer.getOrder();
                actionCount = 2;
                UpdaterAgent.getControllersUpdater().guiUpdate();
                playerIDinWater.remove((Integer) adventurer.getId());

                int x = GameData.getAdventurers()[roundNum].getX();
                int y = GameData.getAdventurers()[roundNum].getY();

                // check win or lose condition, diver and pilot are exceptions
                if (!GameData.getAdventurers()[roundNum].getName().equals("Diver") && !GameData.getAdventurers()[roundNum].getName().equals("Pilot")) {
                    if (x == 0) {
                        if (!GameData.getBoard().getTile(x + 1, y).isExist() && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("[!] No Adjacent Tile To Swim To");
                            return;
                        } else if (GameData.getAdventurers()[roundNum].getName().equals("Explorer") && !GameData.getBoard().getTile(x + 1, y).isExist()
                                && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()
                                && !GameData.getBoard().getTile(x + 1, y - 1).isExist() && !GameData.getBoard().getTile(x + 1, y + 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("[!] No Adjacent Tile To Swim To");
                            return;
                        }

                    } else if (x == 5) {
                        if (!GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("[!] No Adjacent Tile To Swim To");
                            return;
                        } else if (GameData.getAdventurers()[roundNum].getName().equals("Explorer")
                                && !GameData.getBoard().getTile(x - 1, y).isExist()
                                && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()
                                && !GameData.getBoard().getTile(x - 1, y - 1).isExist() && !GameData.getBoard().getTile(x - 1, y + 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("[!] No Adjacent Tile To Swim To");
                            return;
                        }
                    } else if (y == 0) {
                        if (!GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                                && !GameData.getBoard().getTile(x, y + 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("[!] No Adjacent Tile To Swim To");
                            return;
                        } else if (GameData.getAdventurers()[roundNum].getName().equals("Explorer")
                                && !GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                                && !GameData.getBoard().getTile(x, y + 1).isExist()
                                && !GameData.getBoard().getTile(x - 1, y + 1).isExist() && !GameData.getBoard().getTile(x + 1, y + 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("[!] No Adjacent Tile To Swim To");
                            return;
                        }
                    } else if (y == 5) {
                        if (!GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                                && !GameData.getBoard().getTile(x, y - 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("No adjacent tile to swim to");
                            return;
                        } else if (GameData.getAdventurers()[roundNum].getName().equals("Explorer")
                                && !GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                                && !GameData.getBoard().getTile(x, y - 1).isExist()
                                && !GameData.getBoard().getTile(x - 1, y - 1).isExist() && !GameData.getBoard().getTile(x + 1, y - 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("No adjacent tile to swim to");
                            return;
                        }
                    } else {
                        if (!GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                                && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("[!] No Adjacent Tile To Swim To");
                            return;
                        } else if (GameData.getAdventurers()[roundNum].getName().equals("Explorer")
                                && !GameData.getBoard().getTile(x - 1, y).isExist() && !GameData.getBoard().getTile(x + 1, y).isExist()
                                && !GameData.getBoard().getTile(x, y - 1).isExist() && !GameData.getBoard().getTile(x, y + 1).isExist()
                                && !GameData.getBoard().getTile(x - 1, y - 1).isExist() && !GameData.getBoard().getTile(x + 1, y - 1).isExist()
                                && !GameData.getBoard().getTile(x - 1, y + 1).isExist() && !GameData.getBoard().getTile(x + 1, y + 1).isExist()) {
                            Game.GameComplete(false);
                            LogAgent.logMessenger("[!] No Adjacent Tile To Swim To");
                            return;
                        }
                    }
                }

                // save players in order
                LogAgent.logMessenger("Select a nearest tile for [ Player " + (roundNum + 1) + " ] ("
                        + GameData.getAdventurers()[roundNum].getName() + ") to swim to and click [Move To]");
                UpdaterAgent.getPlayerUpdater().guiUpdate();
                UpdaterAgent.getBoardUpdater().guiUpdate();
                break;
            }
        }
    }

    // game complete
    public static void GameComplete(boolean isWin) {
        if (isWin) {
            System.out.println("Game Success");
            LogAgent.logMessenger("[Congrats!] Game Success!\n（＾∀＾●）ﾉｼ");
            if (Constant.AUDIO_ON_OFF) {
                Audio.WIN.Play();
            }
        } else {
            System.out.println("Game failed");
            LogAgent.logMessenger("[Oops!] Game failed...\n＞﹏＜");
            if (Constant.AUDIO_ON_OFF) {
                Audio.FAILURE.Play();
            }
        }
        // disable every component
        try {
            Thread.sleep(1000);
            UpdaterAgent.getBoardUpdater().gameOver();
            UpdaterAgent.getTreasureUpdater().gameOver();
            UpdaterAgent.getWaterMeterUpdater().gameOver();
            UpdaterAgent.getFloodUpdater().gameOver();
            UpdaterAgent.getControllersUpdater().gameOver();
            UpdaterAgent.getPlayerUpdater().gameOver();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // take an action
    public static void doAction() {
        actionCount += 1;
    }

    // take one more action
    public static void moreAction() {
        actionCount -= 1;
    }

    // play players' audio
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

    // getters and setters
    public static void setPlayerIDinWater(ArrayList<Integer> playerIDinWater) {
        Game.playerIDinWater.addAll(playerIDinWater);
    }

    public static int getNumOfPlayer() {
        return numOfPlayer;
    }

    public static int getActionCount() {
        return actionCount;
    }

    public static void setActionCount(int num) {
        actionCount = num;
    }

    public static int getFakeActionCount() {
        return fakeActionCount;
    }

    public static void setFakeActionCount(int fakeActionCount) {
        Game.fakeActionCount = fakeActionCount;
    }

    public static int getRoundNum() {
        return roundNum;
    }

    public static void setRoundNum(int roundNum) {
        Game.roundNum = roundNum;
    }

    public static int getFakeRoundNum() {
        return fakeRoundNum;
    }

    public static void setFakeRoundNum(int fakeRoundNum) {
        Game.fakeRoundNum = fakeRoundNum;
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

    public static boolean isInFakeRound() {
        return inFakeRound;
    }

    public static void setInFakeRound(boolean inFakeRound) {
        Game.inFakeRound = inFakeRound;
    }


}
