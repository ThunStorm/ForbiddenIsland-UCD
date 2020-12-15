package com.esr.gui.listener;

import com.esr.gui.game.BoardPanel;
import com.esr.gui.game.GamePanel;
import com.esr.gui.game.TreasurePanel;
import com.esr.gui.updater.LogAgent;
import com.esr.service.game.GameData;
import com.esr.utils.Map;
import sun.management.Agent;

import javax.swing.*;
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
                GameData.nextTile(Map.coordinatesMatcher.get(BoardPanel.tileCards.indexOf(tile)));
            });
        }
    }

    private void TreasureListener(){
        for (JButton treasure : TreasurePanel.treasureCards){
            treasure.addActionListener(e -> {
                LogAgent.logMessenger(String.valueOf(TreasurePanel.treasureCards.indexOf(treasure)));
            });
        }
    }

    private void PawnListener(){
        for (JButton pawn : GamePanel.playerPawnList) {
            pawn.addActionListener(e -> {
                LogAgent.logMessenger(String.valueOf(GamePanel.playerPawnList.indexOf(pawn)));
            });
        }
    }

    private void HandCardListener(){
        for (JButton p1HandCard : GamePanel.p1HandCards) {
            p1HandCard.addActionListener(e -> {
                LogAgent.logMessenger(String.valueOf(GamePanel.p1HandCards.indexOf(p1HandCard)));
            });
        }
        for (JButton p2HandCard : GamePanel.p2HandCards) {
            p2HandCard.addActionListener(e -> {
                LogAgent.logMessenger(String.valueOf(GamePanel.p2HandCards.indexOf(p2HandCard)));
            });
        }
        for (JButton p3HandCard : GamePanel.p3HandCards) {
            p3HandCard.addActionListener(e -> {
                LogAgent.logMessenger(String.valueOf(GamePanel.p3HandCards.indexOf(p3HandCard)));
            });
        }
        for (JButton p4HandCard : GamePanel.p4HandCards) {
            p4HandCard.addActionListener(e -> {
                LogAgent.logMessenger(String.valueOf(GamePanel.p4HandCards.indexOf(p4HandCard)));
            });
        }
    }
}
