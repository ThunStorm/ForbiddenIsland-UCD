package com.esr.gui.listener;

import com.esr.gui.game.BoardPanel;
import com.esr.gui.game.GamePanel;
import com.esr.gui.game.TreasurePanel;
import com.esr.service.game.GameData;
import com.esr.utils.Map;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Description
 * @Author PJW
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class DataListener {

    // set up click action listeners for buttons
    public DataListener() {
        BoardListener();
        PawnListener();
        HandCardListener();
        TreasureListener();
    }

    private void BoardListener() {
        for (JButton tile : BoardPanel.tileCards) {
            tile.addActionListener(e -> GameData.NextTile(Map.coordinatesMatcher.get(BoardPanel.tileCards.indexOf(tile))));
        }
    }

    private void TreasureListener() {
        for (JButton treasure : TreasurePanel.treasureCards) {
            treasure.addActionListener(e -> GameData.SelectTreasureCard(false, TreasurePanel.treasureCards.indexOf(treasure)));
        }
    }

    private void HandCardListener() {
        for (ArrayList<JButton> individualPlayerCards : GamePanel.playerHandCards) {
            for (JButton handCard : individualPlayerCards) {
                handCard.addActionListener(e -> GameData.SelectTreasureCard(true, individualPlayerCards.indexOf(handCard)));
            }
        }
    }

    private void PawnListener() {
        for (JButton pawn : GamePanel.playerPawnList) {
            pawn.addActionListener(e -> GameData.SelectPawn(GamePanel.playerPawnList.indexOf(pawn)));
        }
    }
}
