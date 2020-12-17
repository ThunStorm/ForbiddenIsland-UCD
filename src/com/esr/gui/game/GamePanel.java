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

    private JPanel gamePanel;
    private PlayerPanel playerPanelUp;
    private PlayerPanel playerPanelDown;
    private BoardPanel boardPanel;
    private TreasurePanel treasurePanel;
    private FloodPanel floodPanel;
    private ArrayList<JButton> p1HandCards;
    private ArrayList<JButton> p2HandCards;
    private ArrayList<JButton> p3HandCards;
    private ArrayList<JButton> p4HandCards;
//    private Subject subject;

    public GamePanel() {
        gamePanel = new JPanel();
        playerPanelUp = new PlayerPanel();
        playerPanelDown = new PlayerPanel();
        boardPanel = new BoardPanel();
        treasurePanel = new TreasurePanel();
        floodPanel = new FloodPanel();
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

        p1HandCards = new ArrayList<>();
        p2HandCards = new ArrayList<>();
        p3HandCards = new ArrayList<>();
        p4HandCards = new ArrayList<>();
        p1HandCards.addAll(playerPanelDown.getP1HandCards());
        p2HandCards.addAll(playerPanelDown.getP2HandCards());
        p3HandCards.addAll(playerPanelUp.getP1HandCards());
        p4HandCards.addAll(playerPanelUp.getP2HandCards());

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