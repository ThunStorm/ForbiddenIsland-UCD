package com.esr.gui.game;

import com.esr.adventurer.Adventurer;
import com.esr.gui.game.*;
import com.esr.gui.updater.TileUpdater;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel {
    private JPanel gamePanel;
    private ArrayList<Integer> tileOrder;
    private ArrayList<Adventurer> adventurers;
    private ArrayList<JButton> treasureCards;
    private PlayerPanel playerPanelUp;
    private PlayerPanel playerPanelDown;
    private BoardPanel boardPanel;
    private TreasurePanel treasurePanel;
    private FloodPanel floodPanel;
    private TileUpdater tileUpdater;
    private int NumOfPlayers;
//    private Subject subject;

    public GamePanel(ArrayList<Integer> tileOrder, ArrayList<Adventurer> adventurers) {
        this.tileOrder = tileOrder;
        this.adventurers = adventurers;
        gamePanel = new JPanel();
        playerPanelUp = new PlayerPanel(adventurers);
        playerPanelDown = new PlayerPanel(adventurers);
        boardPanel = new BoardPanel();
        treasurePanel = new TreasurePanel();
        floodPanel = new FloodPanel();
        gamePanel.setLayout(new BorderLayout(5, 5));
        gamePanel.add(playerPanelDown.getDuoPlayerPanel(), BorderLayout.SOUTH);
        gamePanel.add(playerPanelUp.getDuoPlayerPanel(), BorderLayout.NORTH);
        gamePanel.add(floodPanel.getFloodPanel(), BorderLayout.EAST);
        gamePanel.add(treasurePanel.getTreasurePanel(), BorderLayout.WEST);
        gamePanel.add(boardPanel.getBoard(), BorderLayout.CENTER);
        init();
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void init() {

        playerPanelDown.setUpPlayerPanel(Math.min(adventurers.size(), 2), 0);
        playerPanelUp.setUpPlayerPanel(Math.max(adventurers.size() - 2,0), 2);

        boardPanel.setBoardBTN(tileOrder, adventurers);
        floodPanel.setUpFloodCard();
        treasurePanel.SetUpTreasureCard();
        tileUpdater = new TileUpdater(boardPanel.getTileCards());
        treasureCards = treasurePanel.getTreasureCards();
    }
}