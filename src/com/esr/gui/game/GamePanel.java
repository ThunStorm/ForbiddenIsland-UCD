package com.esr.gui.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel {
    public static ArrayList<JButton> playerPawnList;
    public static ArrayList<JButton> p1HandCards;
    public static ArrayList<JButton> p2HandCards;
    public static ArrayList<JButton> p3HandCards;
    public static ArrayList<JButton> p4HandCards;

    private JPanel gamePanel;
    private PlayerPanel playerPanelUp;
    private PlayerPanel playerPanelDown;
    private BoardPanel boardPanel;
    private TreasurePanel treasurePanel;
    private FloodPanel floodPanel;
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

        p1HandCards = playerPanelDown.getP1HandCards();
        p2HandCards = playerPanelDown.getP2HandCards();
        p3HandCards = playerPanelUp.getP1HandCards();
        p4HandCards = playerPanelUp.getP2HandCards();
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

}