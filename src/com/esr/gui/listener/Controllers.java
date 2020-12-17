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
import com.esr.utils.Map;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description
 * @Author PJW
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class Controllers {
    public Controllers() {
        MoveToController();
        LiftOffController();
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
                    UpdaterAgent.getBoardUpdater().guiUpdate();
                    Game.doAction();
                }
                else{ LogAgent.logMessenger("Can't Move To this tile"); }
            }
            else { LogAgent.logMessenger("Maximum actions succeed"); }
        });
    }

    private void LiftOffController(){
        ConsolePanel.consoleButtons.get(2).addActionListener(e -> {
            LogAgent.logMessenger("Lift Off");
            int idx14 = GameData.getTilesArray().indexOf(14);
            Block block14 = GameData.getBoard().getTile(Map.coordinatesMatcher.get(idx14)[0],Map.coordinatesMatcher.get(idx14)[1]);
            if (block14.isExist() && block14.getPlayerOnBoard().size() == Game.getNumOfPlayer()){
                ArrayList<Integer> handCards = new ArrayList<>();
                ArrayList<TreasureFigurines> figurines = new ArrayList<>();
                for (Adventurer adventurer : GameData.getAdventurers()){
                    handCards.addAll(adventurer.getHandCards());
                    figurines.addAll(adventurer.getCapturedFigurines());
                }
                if ((handCards.contains(20) || handCards.contains(21) || handCards.contains(22)) && figurines.size() == 4){
                    Game.GameComplete(true);
                }
                else { System.out.println("Lift Off failed"); }
            }
            else{ System.out.println("Fool's landing no more exists"); }
        });
    }

    private void ShoreUpController(){
        ConsolePanel.consoleButtons.get(3).addActionListener(e -> {
            if (Game.getActionCount() < 3){
                if (GameData.getBoard().isCanShoreUp()){
                    GameData.ShoreUp();
                    LogAgent.logMessenger("Shore Up " + Arrays.toString(Map.coordinatesMatcher.get(GameData.getAdventurers()[Game.getRoundNum()].getPos())));
                    UpdaterAgent.getBoardUpdater().guiUpdate();
                    Game.doAction();
                    if (GameData.getAdventurers()[Game.getRoundNum()] instanceof Engineer){
                        if(((Engineer) GameData.getAdventurers()[Game.getRoundNum()]).getShoreUpCount() > 0){
                            ((Engineer) GameData.getAdventurers()[Game.getRoundNum()]).ShoreUp();
                            Game.moreAction();
                        }
                    }
                }
                else { LogAgent.logMessenger("Can't Shore Up this tile"); }
            }
        });
    }

    private void PassToController(){
        ConsolePanel.consoleButtons.get(4).addActionListener(e -> {
//            LogAgent.logMessenger("Pass To");
            if (Game.getActionCount() < 3 && GameData.getSelectedPawn() != -1){
                if (GameData.getBoard().getTile(GameData.getAdventurers()[Game.getRoundNum()].getX(),GameData.getAdventurers()[Game.getRoundNum()].getY()).CanPassTo(GameData.getAdventurers()[Game.getRoundNum()], GameData.getAdventurers()[GameData.getSelectedPawn()])){
                    GameData.PassTo();
                    LogAgent.logMessenger(GameData.getAdventurers()[Game.getRoundNum()].getName()
                            + " passed a card to " + GameData.getAdventurers()[GameData.getSelectedPawn()].getName());
                    UpdaterAgent.getPlayerUpdater().guiUpdate();
                    UpdaterAgent.getTreasureUpdater().guiUpdate();
                    Game.doAction();
                    GameData.SelectPawn(-1);
                }
                else{ LogAgent.logMessenger("Can't do Pass To Action"); }
            }
            else { LogAgent.logMessenger("Maximum actions succeed or no selected receiver"); }
        });
    }

    private void CaptureController(){
        ConsolePanel.consoleButtons.get(5).addActionListener(e -> {
//            LogAgent.logMessenger("Capture");
            ArrayList<Integer> handCards = new ArrayList<>(GameData.getAdventurers()[Game.getRoundNum()].getHandCards());
            int[] treasureCount = {0, 0, 0, 0};
            for (int handCard : handCards){
                if (handCard >= 0 && handCard <= 4){ treasureCount[0]++; }
                else if (handCard >= 5 && handCard <= 9){ treasureCount[1]++;}
                else if (handCard >= 10 && handCard <= 14){ treasureCount[2]++;}
                else if (handCard >= 15 && handCard <= 19){ treasureCount[3]++;}
            }
            for (int i = 0; i < treasureCount.length; i++) {
                if (treasureCount[i] == 4){
                    Block block = GameData.getBoard().getTile(GameData.getAdventurers()[Game.getRoundNum()].getX(),GameData.getAdventurers()[Game.getRoundNum()].getY());
                    if (block.getTileId() == 2*i+1 || block.getTileId() == 2*i+2){
                        GameData.getBoard().getTile(GameData.getAdventurers()[Game.getRoundNum()].getX(),GameData.getAdventurers()[Game.getRoundNum()].getY()).setCaptured();
                        for(TreasureFigurines figurine : TreasureFigurines.values()){
                            if (figurine.ordinal() == i){
                                GameData.getAdventurers()[Game.getRoundNum()].setCapturedFigurines(figurine);
                                for (int handCardNo : GameData.getAdventurers()[Game.getRoundNum()].getHandCards()){
                                    //TODO Carefully use remove https://www.cnblogs.com/dolphin0520/p/3933551.html
                                    if(handCardNo >= i * 5 && handCardNo <= i * 5 + 4){
                                        GameData.getAdventurers()[Game.getRoundNum()].getHandCards().remove((Integer)handCardNo);
                                        GameData.getTreasureDeck().Discard(handCardNo);
                                    }
                                }
                            }
                        }
                        GameData.getBoard().getTile(Map.coordinatesMatcher.get(GameData.getTilesArray().indexOf(2*i+1))[0],
                                Map.coordinatesMatcher.get(GameData.getTilesArray().indexOf(2*i+1))[1]).setCaptured();
                        GameData.getBoard().getTile(Map.coordinatesMatcher.get(GameData.getTilesArray().indexOf(2*i+2))[0],
                                Map.coordinatesMatcher.get(GameData.getTilesArray().indexOf(2*i+2))[1]).setCaptured();
                    }
                }
            }
            UpdaterAgent.getPlayerUpdater().guiUpdate();
            UpdaterAgent.getBoardUpdater().guiUpdate();
        });
    }

    private void NextController(){
        ConsolePanel.consoleButtons.get(6).addActionListener(e -> {
            LogAgent.logMessenger("Next");
            if(!Game.isStage23Done()){
                Game.Stage23();
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
            if(GameData.getCardsInRound().size() != 0){
                ArrayList<Integer> allCardsInRound = new ArrayList<>();
                allCardsInRound.addAll(GameData.getAdventurers()[Game.getRoundNum()].getHandCards());
                allCardsInRound.addAll(GameData.getDisplayedTreasureCard());
                GameData.getAdventurers()[Game.getRoundNum()].getHandCards().clear();
                GameData.getDisplayedTreasureCard().clear();
                for (int card : allCardsInRound){
                    if(GameData.getCardsInRound().contains(card)){
                       GameData.getAdventurers()[Game.getRoundNum()].getHandCards().add(card);
                    }
                    else {
                        GameData.getTreasureDeck().Discard(card);
                    }
                }
                GameData.resetCardsInRound();
                UpdaterAgent.getPlayerUpdater().guiUpdate();
                UpdaterAgent.getTreasureUpdater().guiUpdate();
            }
        });
    }

}
