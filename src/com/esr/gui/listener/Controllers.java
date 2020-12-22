package com.esr.gui.listener;

import com.esr.gui.console.ConsolePanel;
import com.esr.gui.updater.LogAgent;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.Game;
import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.service.game.component.adventurer.Engineer;
import com.esr.service.game.component.cards.TreasureFigurines;
import com.esr.service.game.data.Block;
import com.esr.service.game.data.TileStatus;
import com.esr.utils.Audio;
import com.esr.utils.Constant;
import com.esr.utils.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @Description
 * @Author PJW
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class Controllers {
    public Controllers() {
        MoveToController();
        SpecialActionController();
        ShoreUpController();
        PassToController();
        CaptureController();
        NextController();
        DiscardController();
    }

    private void MoveToController(){
        ConsolePanel.consoleButtons.get(1).addActionListener(e -> {
            if (Game.getActionCount() < 3){
                if (GameData.getBoard().isCanMove()){
                    GameData.MoveTo();
                    LogAgent.logMessenger("Move To " + Arrays.toString(Map.coordinatesMatcher.get(GameData.getAdventurers()[Game.getRoundNum()].getPos())));
                    if (Constant.AUDIO_ON_OFF){ Audio.MOVETO.Play(); }
                    UpdaterAgent.getBoardUpdater().guiUpdate();
                    Game.doAction();
                    if (Game.isNeed2save()){
                        Game.SavePlayersRound();
                    }
                }
                else{ LogAgent.logMessenger("Can't Move To this tile"); }
            }
            else { LogAgent.logMessenger("Maximum actions succeed"); }
        });
    }

    private void ShoreUpController(){
        ConsolePanel.consoleButtons.get(2).addActionListener(e -> {
            if (Game.getActionCount() < 3){
                if (GameData.getBoard().isCanShoreUp()){
                    GameData.ShoreUp();
                    LogAgent.logMessenger("Shore Up " + Arrays.toString(Map.coordinatesMatcher.get(GameData.getAdventurers()[Game.getRoundNum()].getPos())));
                    UpdaterAgent.getBoardUpdater().guiUpdate();
                    if (Constant.AUDIO_ON_OFF){ Audio.SHOREUP.Play(); }
                    Game.doAction();
                    if (GameData.getAdventurers()[Game.getRoundNum()] instanceof Engineer){
                        if(((Engineer) GameData.getAdventurers()[Game.getRoundNum()]).getShoreUpCount() > 0){
                            ((Engineer) GameData.getAdventurers()[Game.getRoundNum()]).ShoreUp();
                            Game.moreAction();
                            if (Constant.AUDIO_ON_OFF){ Audio.SHOREUP.Play(); }
                        }
                    }
                }
                else { LogAgent.logMessenger("Can't Shore Up this tile"); }
            }
        });
    }

    private void PassToController(){
        ConsolePanel.consoleButtons.get(3).addActionListener(e -> {
//            LogAgent.logMessenger("Pass To");
            if (Game.getActionCount() < 3 && GameData.getSelectedPawn() != -1){
                if (GameData.getBoard().getTile(GameData.getAdventurers()[Game.getRoundNum()].getX(),GameData.getAdventurers()[Game.getRoundNum()].getY())
                        .CanPassTo(GameData.getAdventurers()[Game.getRoundNum()], GameData.getAdventurers()[GameData.getSelectedPawn()])
                        || GameData.getAdventurers()[Game.getRoundNum()].getName().equals("Messenger")){
                    if(GameData.PassTo()){
                        LogAgent.logMessenger(GameData.getAdventurers()[Game.getRoundNum()].getName()
                                + " passed a card to " + GameData.getAdventurers()[GameData.getSelectedPawn()].getName());
                        if (Constant.AUDIO_ON_OFF){ Audio.PASSTO.Play(); }
                        UpdaterAgent.getPlayerUpdater().guiUpdate();
                        UpdaterAgent.getTreasureUpdater().guiUpdate();
                        Game.doAction();
                        GameData.SelectPawn(-1);
                        GameData.resetCardsInRound();
                    }
                }
                else{ LogAgent.logMessenger("Can't do Pass To Action"); }
            }
            else { LogAgent.logMessenger("Maximum actions succeed or no selected receiver"); }
        });
    }

    private void CaptureController(){
        ConsolePanel.consoleButtons.get(4).addActionListener(e -> {
//            LogAgent.logMessenger("Capture");
            if (Game.getActionCount() < 3) {
                ArrayList<Integer> handCards = new ArrayList<>(GameData.getAdventurers()[Game.getRoundNum()].getHandCards());
                int[] treasureCount = {0, 0, 0, 0};
                for (int handCard : handCards) {
                    if (handCard >= 0 && handCard <= 4) {
                        treasureCount[0]++;
                    } else if (handCard >= 5 && handCard <= 9) {
                        treasureCount[1]++;
                    } else if (handCard >= 10 && handCard <= 14) {
                        treasureCount[2]++;
                    } else if (handCard >= 15 && handCard <= 19) {
                        treasureCount[3]++;
                    }
                }
                for (int i = 0; i < treasureCount.length; i++) {
                    if (treasureCount[i] == 4) {
                        Block block = GameData.getBoard().getTile(GameData.getAdventurers()[Game.getRoundNum()].getX(), GameData.getAdventurers()[Game.getRoundNum()].getY());
                        if (block.getTileId() == 2 * i + 1 || block.getTileId() == 2 * i + 2) {
                            GameData.getBoard().getTile(GameData.getAdventurers()[Game.getRoundNum()].getX(), GameData.getAdventurers()[Game.getRoundNum()].getY()).setCaptured();
                            for (TreasureFigurines figurine : TreasureFigurines.values()) {
                                if (figurine.ordinal() == i) {
                                    GameData.getAdventurers()[Game.getRoundNum()].setCapturedFigurines(figurine);
                                    Iterator<Integer> iterator = GameData.getAdventurers()[Game.getRoundNum()].getHandCards().iterator();
                                    while (iterator.hasNext()) {
                                        Integer handCardNo = iterator.next();
                                        if (handCardNo >= i * 5 && handCardNo <= i * 5 + 4) {
                                            iterator.remove();
                                            if (Constant.AUDIO_ON_OFF) {
                                                Audio.CAPTURE.Play();
                                            }
                                            GameData.getTreasureDeck().Discard(handCardNo);
                                        }
                                    }
                                }
                            }
                            GameData.getBoard().getTile(Map.coordinatesMatcher.get(GameData.getTilesArray().indexOf(2 * i + 1))[0],
                                    Map.coordinatesMatcher.get(GameData.getTilesArray().indexOf(2 * i + 1))[1]).setCaptured();
                            GameData.getBoard().getTile(Map.coordinatesMatcher.get(GameData.getTilesArray().indexOf(2 * i + 2))[0],
                                    Map.coordinatesMatcher.get(GameData.getTilesArray().indexOf(2 * i + 2))[1]).setCaptured();
                        }
                    }
                }
                UpdaterAgent.getPlayerUpdater().guiUpdate();
                UpdaterAgent.getBoardUpdater().guiUpdate();
            }
        });
    }

    private void SpecialActionController(){
        ConsolePanel.consoleButtons.get(5).addActionListener(e -> {
            LogAgent.logMessenger("Lift Off");
            int idx14 = GameData.getTilesArray().indexOf(14);
            Block block14 = GameData.getBoard().getTile(Map.coordinatesMatcher.get(idx14)[0],Map.coordinatesMatcher.get(idx14)[1]);
            ArrayList<Integer> handCards = new ArrayList<>();
            ArrayList<TreasureFigurines> figurines = new ArrayList<>();
            for (Adventurer adventurer : GameData.getAdventurers()){
                handCards.addAll(adventurer.getHandCards());
                figurines.addAll(adventurer.getCapturedFigurines());
            }
            if (GameData.getCardsInRound() != null && !GameData.getCardsInRound().isEmpty() && GameData.getSpecialActionTile()[0]!=-1 && GameData.getSpecialActionTile()[1] != -1){
                int lastSelect = GameData.getCardsInRound().get(GameData.getCardsInRound().size()-1);
                //sandbag
                if (lastSelect == 23 || lastSelect == 24){
                    Block shoredTile = GameData.getBoard().getTile(GameData.getSpecialActionTile()[0],GameData.getSpecialActionTile()[1]);
                    if (shoredTile.isExist() && shoredTile.getStatus() == TileStatus.Flooded){
                        GameData.getBoard().getTile(GameData.getSpecialActionTile()[0],GameData.getSpecialActionTile()[1]).ShoreUp();
                        GameData.getAdventurers()[Game.getRoundNum()].getHandCards().remove((Integer)lastSelect);
                        GameData.getTreasureDeck().Discard(lastSelect);
                        GameData.resetCardsInRound();
                        GameData.resetSpecialActionTile();
                        UpdaterAgent.getBoardUpdater().guiUpdate();
                        UpdaterAgent.getPlayerUpdater().guiUpdate();
                        if(Constant.AUDIO_ON_OFF){
                            Audio.SHOREUP.Play();
                        }
                    }
                }
                // fly to
                else if ((lastSelect == 20 || lastSelect == 21 || lastSelect == 22) && GameData.getSelectedPawns().size() != 0){
                    GameData.getBoard().getTile(GameData.getAdventurers()[Game.getRoundNum()].getX(),GameData.getAdventurers()[Game.getRoundNum()].getY()).MoveOff(GameData.getAdventurers()[Game.getRoundNum()]);
                    GameData.getAdventurers()[Game.getRoundNum()].setPos(GameData.getSpecialActionTile()[0],GameData.getSpecialActionTile()[1]);
                    GameData.getBoard().getTile(GameData.getSpecialActionTile()[0],GameData.getSpecialActionTile()[1]).MoveOnto(GameData.getAdventurers()[Game.getRoundNum()].getId());
                    for (int pawn : GameData.getSelectedPawns()){
                        GameData.getBoard().getTile(GameData.getAdventurers()[pawn].getX(),GameData.getAdventurers()[pawn].getY()).MoveOff(GameData.getAdventurers()[pawn]);
                        GameData.getAdventurers()[pawn].setPos(GameData.getSpecialActionTile()[0],GameData.getSpecialActionTile()[1]);
                        GameData.getBoard().getTile(GameData.getSpecialActionTile()[0],GameData.getSpecialActionTile()[1]).MoveOnto(GameData.getAdventurers()[pawn].getId());
                    }
                    GameData.getAdventurers()[Game.getRoundNum()].getHandCards().remove((Integer)lastSelect);
                    GameData.getTreasureDeck().Discard(lastSelect);
                    GameData.SelectPawn(-1);
                    GameData.resetCardsInRound();
                    GameData.resetSpecialActionTile();
                    UpdaterAgent.getBoardUpdater().guiUpdate();
                    UpdaterAgent.getPlayerUpdater().guiUpdate();
                    if(Constant.AUDIO_ON_OFF){
                        Audio.LIFTOFF.Play();
                    }
                }

                if(Game.isInFakeRound()){
                    Game.setInFakeRound(false);
                    Game.setRoundNum(Game.getFakeRoundNum());
                    Game.setFakeRoundNum(-1);
                    Game.setActionCount(Game.getFakeActionCount());
                    Game.setFakeActionCount(-1);
                    UpdaterAgent.getBoardUpdater().guiUpdate();
                    UpdaterAgent.getPlayerUpdater().guiUpdate();
                    LogAgent.logMessenger("Back to player " + (Game.getRoundNum()+1) +"'s turn (" + GameData.getAdventurers()[Game.getRoundNum()].getName()+ ")");
                    LogAgent.logMessenger("Have done " + Game.getActionCount() + " actions");
                }
            }
            // lift off
            else if (block14.isExist() && block14.getPlayerOnBoard().size() == Game.getNumOfPlayer()){
                if ((handCards.contains(20) || handCards.contains(21) || handCards.contains(22)) && figurines.size() == 4){
                    if (Constant.AUDIO_ON_OFF){ Audio.LIFTOFF.Play(); }
                    Game.GameComplete(true);
                }
                else { System.out.println("Lift Off failed"); }
            }
            // Enter in fake round
            else if (GameData.getSelectedPawn() != -1){
                Game.setInFakeRound(true);
                Game.setFakeRoundNum(Game.getRoundNum());
                Game.setFakeActionCount(Game.getActionCount());
                Game.setActionCount(3);
                Game.setRoundNum(GameData.getSelectedPawn());
                UpdaterAgent.getPlayerUpdater().guiUpdate();
                LogAgent.logMessenger("Switch to player " + (Game.getRoundNum()+1) +"'s turn (" + GameData.getAdventurers()[Game.getRoundNum()].getName()+ ")");
            }
            else{ System.out.println("Error"); }
        });
    }

    private void NextController(){
        ConsolePanel.consoleButtons.get(6).addActionListener(e -> {
            LogAgent.logMessenger("Next");
            if (Constant.AUDIO_ON_OFF){ Audio.NEXT.Play(); }
            if(!Game.isStage23Done()){
                Game.Stage23();
            }
            else if (Game.isNeed2save()){
                LogAgent.logMessenger("Please save adventures first");
            }
            else{
                Game.RoundEnd();
            }
        });
    }
//
    private void DiscardController(){
        ConsolePanel.consoleButtons.get(7).addActionListener(e -> {
            LogAgent.logMessenger("Discard");
            if(GameData.getCardsInRound().size() != 0 && !Game.isInFakeRound()){
                ArrayList<Integer> allCardsInRound = new ArrayList<>();
                allCardsInRound.addAll(GameData.getAdventurers()[Game.getRoundNum()].getHandCards());
                allCardsInRound.addAll(GameData.getDisplayedTreasureCard());
                GameData.getAdventurers()[Game.getRoundNum()].getHandCards().clear();
                GameData.getDisplayedTreasureCard().clear();
                for (Integer card : allCardsInRound){
                    if (GameData.getCardsInRound().contains(card)){
                        GameData.getTreasureDeck().Discard(card);
                    }
                    else {
                        GameData.getAdventurers()[Game.getRoundNum()].getHandCards().add(card);
                    }
                }
                Iterator<Integer> iterator = GameData.getAdventurers()[Game.getRoundNum()].getHandCards().iterator();
                int count = 0;
                while (iterator.hasNext()){
                    int handCard = iterator.next();
                    if(count >= 5){
                       GameData.getDisplayedTreasureCard().add(handCard);
                       iterator.remove();
                    }
                    count++;
                }
                GameData.resetCardsInRound();
                UpdaterAgent.getPlayerUpdater().guiUpdate();
                UpdaterAgent.getTreasureUpdater().guiUpdate();
                if (Constant.AUDIO_ON_OFF){ Audio.DISCARD.Play(); }
            }
            else if(Game.isInFakeRound()){
                ArrayList<Integer> allCardsInRound = new ArrayList<>(GameData.getAdventurers()[Game.getRoundNum()].getHandCards());
                GameData.getAdventurers()[Game.getRoundNum()].getHandCards().clear();
                for (Integer card : allCardsInRound){
                    if (GameData.getCardsInRound().contains(card)){
                        GameData.getTreasureDeck().Discard(card);
                    }
                    else {
                        GameData.getAdventurers()[Game.getRoundNum()].getHandCards().add(card);
                    }
                }
                Game.setInFakeRound(false);
                Game.setActionCount(Game.getFakeActionCount());
                Game.setFakeActionCount(-1);
                Game.setRoundNum(Game.getFakeRoundNum());
                Game.setFakeRoundNum(-1);
                GameData.SelectPawn(-1);
                UpdaterAgent.getPlayerUpdater().guiUpdate();
                UpdaterAgent.getTreasureUpdater().guiUpdate();
                if (Constant.AUDIO_ON_OFF){ Audio.DISCARD.Play(); }
            }
            else {
                LogAgent.logMessenger("Please select the cards you would like to discard");
            }
        });
    }

}
