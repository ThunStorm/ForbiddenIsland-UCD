package com.esr.gui.game;

import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class FloodPanel {
    public static ArrayList<JButton> floodCards;
    private final JPanel floodPanel;

    // create a panel for displaying flood card
    public FloodPanel() {
        floodPanel = new JPanel(new GridLayout(8, 1, 1, 3));
        JLabel pile = new JLabel();
        Dimension floodCardSize = new Dimension(Constant.FLOOD_WIDTH, Constant.FLOOD_HEIGHT);
        pile.setPreferredSize(floodCardSize);
        pile.setIcon(new ImageIcon(CommonUtils.getImage("/Back/Flood Discard.png", Constant.FLOOD_WIDTH, Constant.FLOOD_HEIGHT)));
        floodPanel.add(pile);
        JLabel back = new JLabel();
        back.setPreferredSize(floodCardSize);
        back.setIcon(new ImageIcon(CommonUtils.getImage("/Back/Flood Deck.png", Constant.FLOOD_WIDTH, Constant.FLOOD_HEIGHT, 90d)));
        floodPanel.add(back);
        floodCards = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            floodCards.add(new JButton());
            floodCards.get(i).setPreferredSize(floodCardSize);
            floodCards.get(i).setVisible(false);
            floodPanel.add(floodCards.get(i));
        }
    }

    public JPanel getFloodPanel() {
        return floodPanel;
    }
}
