package com.esr.gui;

import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FloodPanel {
    private Dimension floodCardSize = new Dimension(Constant.FLOOD_WIDTH, Constant.FLOOD_HEIGHT);
    private ArrayList<JButton> floodCards;
    private JPanel floodPanel;

    public FloodPanel(){
        initPile();
    }
    private void initPile(){
        floodPanel = new JPanel(new GridLayout(8, 1, 1, 3));
        JLabel pile = new JLabel();
        pile.setPreferredSize(floodCardSize);
        pile.setIcon(new ImageIcon(CommonUtils.getImage("/Back/Flood Discard.png", Constant.FLOOD_WIDTH, Constant.FLOOD_HEIGHT)));
        floodPanel.add(pile);
        JLabel back = new JLabel();
        back.setPreferredSize(floodCardSize);
        back.setIcon(new ImageIcon(CommonUtils.getImage("/Back/Flood Deck.png", Constant.FLOOD_WIDTH, Constant.FLOOD_HEIGHT, 90d)));
        floodPanel.add(back);
    }
    public void setUpFloodCard(){
        floodCards = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            floodCards.add(new JButton());
            floodCards.get(i).setPreferredSize(floodCardSize);
            floodCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("/Tiles/" + Integer.toString(i + 1) + ".png", Constant.FLOOD_WIDTH, Constant.FLOOD_HEIGHT)));
            floodPanel.add(floodCards.get(i));
        }
    }

    public JPanel getFloodPanel() {
        return floodPanel;
    }

    public ArrayList<JButton> getFloodCards() {
        return floodCards;
    }
}
