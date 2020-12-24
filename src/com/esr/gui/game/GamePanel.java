package com.esr.gui.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class GamePanel {
    public static ArrayList<JButton> playerPawnList;
    public static ArrayList<ArrayList<JButton>> playerHandCards;
    private final JPanel gamePanel;

    // add sub-panels to the game panel
    public GamePanel() {
        PlayerPanel playerPanelUp = new PlayerPanel();
        PlayerPanel playerPanelDown = new PlayerPanel();
        BoardPanel boardPanel = new BoardPanel();
        TreasurePanel treasurePanel = new TreasurePanel();
        FloodPanel floodPanel = new FloodPanel();
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout(5, 5));
        gamePanel.add(playerPanelDown.getDuoPlayerPanel(), BorderLayout.SOUTH);
        gamePanel.add(playerPanelUp.getDuoPlayerPanel(), BorderLayout.NORTH);
        gamePanel.add(floodPanel.getFloodPanel(), BorderLayout.EAST);
        gamePanel.add(treasurePanel.getTreasurePanel(), BorderLayout.WEST);
        gamePanel.add(boardPanel.getBoard(), BorderLayout.CENTER);

        playerPawnList = new ArrayList<>();
        playerPawnList.add(playerPanelDown.getP1Pawn());
        playerPawnList.add(playerPanelDown.getP2Pawn());
        playerPawnList.add(playerPanelUp.getP1Pawn());
        playerPawnList.add(playerPanelUp.getP2Pawn());

        ArrayList<JButton> p1HandCards = new ArrayList<>(playerPanelDown.getP1HandCards());
        ArrayList<JButton> p2HandCards = new ArrayList<>(playerPanelDown.getP2HandCards());
        ArrayList<JButton> p3HandCards = new ArrayList<>(playerPanelUp.getP1HandCards());
        ArrayList<JButton> p4HandCards = new ArrayList<>(playerPanelUp.getP2HandCards());

        playerHandCards = new ArrayList<>();
        playerHandCards.add(p1HandCards);
        playerHandCards.add(p2HandCards);
        playerHandCards.add(p3HandCards);
        playerHandCards.add(p4HandCards);
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

}