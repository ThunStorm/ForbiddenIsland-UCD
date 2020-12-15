package com.esr.gui.listener;

import com.esr.gui.console.ConsolePanel;
import com.esr.gui.updater.LogAgent;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.Game;
import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.service.game.component.cards.TreasureFigurines;
import com.esr.service.game.data.Block;
import com.esr.service.game.data.FigurinesData;
import com.esr.utils.Map;

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
        ClearController();
    }

    private void NextController(){
        ConsolePanel.consoleButtons.get(6).addActionListener(e -> {
            LogAgent.logMessenger("Next");
            Game.MainGame();
        });
    }

    private void MoveToController(){
        ConsolePanel.consoleButtons.get(1).addActionListener(e -> {
            if (Game.getActionCount()<3){
                if (GameData.getBoard().isCanMove()){
                    GameData.moveTo();
                    LogAgent.logMessenger("Move To" + Arrays.toString(Map.coordinatesMatcher.get(GameData.getAdventurers()[Game.getRoundNum()].getPos())));
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
                    figurines.addAll(adventurer.getFigurines());
                }
                if ((handCards.contains(20) || handCards.contains(21) || handCards.contains(22)) && figurines.size() == 4){
                    Game.GameComplete();
                }
                else { System.out.println("Lift Off failed"); }
            }
            else{ System.out.println("Fool's landing no more exists"); }
        });
    }

    private void ShoreUpController(){
        ConsolePanel.consoleButtons.get(3).addActionListener(e -> {
            LogAgent.logMessenger("Shore Up");
        });
    }

    private void PassToController(){
        ConsolePanel.consoleButtons.get(4).addActionListener(e -> {
            LogAgent.logMessenger("Pass To");
        });
    }

    private void CaptureController(){
        ConsolePanel.consoleButtons.get(5).addActionListener(e -> {
            LogAgent.logMessenger("Capture");
        });
    }

    private void ClearController(){
        ConsolePanel.consoleButtons.get(7).addActionListener(e -> {
            LogAgent.logMessenger("Clear");
        });
    }

}
