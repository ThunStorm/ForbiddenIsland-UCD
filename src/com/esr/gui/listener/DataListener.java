package com.esr.gui.listener;

import com.esr.gui.game.BoardPanel;
import com.esr.gui.game.GamePanel;
import com.esr.gui.game.TreasurePanel;
import com.esr.gui.updater.LogAgent;
import com.esr.service.game.Game;
import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.utils.Map;
import sun.management.Agent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description
 * @Author PJW
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class DataListener {

    public DataListener() {
        BoardListener();
        PawnListener();
        HandCardListener();
        TreasureListener();
    }

    private void BoardListener(){
        for (JButton tile : BoardPanel.tileCards) {
            tile.addActionListener(e -> {
//                LogAgent.logMessenger(Arrays.toString(Map.coordinatesMatcher.get(BoardPanel.tileCards.indexOf(tile))));
                GameData.NextTile(Map.coordinatesMatcher.get(BoardPanel.tileCards.indexOf(tile)));
            });
        }
    }

    private void TreasureListener(){
        for (JButton treasure : TreasurePanel.treasureCards){
            treasure.addActionListener(e -> {
//                LogAgent.logMessenger(String.valueOf(TreasurePanel.treasureCards.indexOf(treasure)));
                // deal with select blank
                GameData.SelectTreasureCard(false, TreasurePanel.treasureCards.indexOf(treasure));

            });
        }
    }

    private void HandCardListener(){
        for (ArrayList<JButton> individualPlayerCards: GamePanel.playerHandCards){
            for (JButton handCard : individualPlayerCards){
                handCard.addActionListener(e -> {
//                    LogAgent.logMessenger(String.valueOf(individualPlayerCards.indexOf(handCard)));
                    GameData.SelectTreasureCard(true, individualPlayerCards.indexOf(handCard));
                });
            }
        }
    }

    private void PawnListener(){
        for (JButton pawn : GamePanel.playerPawnList) {
            pawn.addActionListener(e -> {
//                LogAgent.logMessenger(String.valueOf(GamePanel.playerPawnList.indexOf(pawn)));
                GameData.SelectPawn(GamePanel.playerPawnList.indexOf(pawn));
            });
        }
    }
}
