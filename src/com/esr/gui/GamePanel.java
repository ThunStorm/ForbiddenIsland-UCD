package com.esr.gui;

import com.esr.service.observer.Observer;
import com.esr.service.observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel {
    private JPanel gamePanel;
    private ArrayList<JButton> tileCards;
    private ArrayList<JButton> treasureCards;
    private PlayerPanel playerPanelUp;
    private PlayerPanel playerPanelDown;
    private BoardPanel boardPanel;
    private TreasurePanel treasurePanel;
    private FloodPanel floodPanel;
    private Subject subject;

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

        tileCards = boardPanel.getTileCards();
        treasureCards = treasurePanel.getTreasureCards();
        update();

//        setUpPanels();
//        System.out.println(tileCards.get(3).toString() + treasureCards.get(1).toString());
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void update() {
        playerPanelUp.setUpPlayerPanel();
        playerPanelDown.setUpPlayerPanel();
        boardPanel.setBoardBTN();
        floodPanel.setUpFloodCard();
        treasurePanel.SetUpTreasureCard();
    }
}