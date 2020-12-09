package com.esr.gui.game;

import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TreasurePanel {

    public static JLabel waterMeter = new JLabel();
    public static ArrayList<JButton> treasureCards;

    private Dimension treasureCardSize = new Dimension(Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT);
    private JPanel treasureCardPile;
    private JPanel treasurePanel;


    public TreasurePanel() {
        treasurePanel = new JPanel(new BorderLayout(1, 3));
        treasureCardPile = new JPanel(new GridLayout(4, 1, 1, 3));
        treasureCards = new ArrayList<>();

        JLabel pile = new JLabel();
        pile.setPreferredSize(treasureCardSize);
        pile.setIcon(new ImageIcon(CommonUtils.getImage("/Back/Treasure Discard.png", Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT)));
        treasureCardPile.add(pile);
        JLabel back = new JLabel();
        back.setPreferredSize(treasureCardSize);
        back.setIcon(new ImageIcon(CommonUtils.getImage("/Back/Treasure Deck.png", Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT, 90d)));
        treasureCardPile.add(back);

        for (int i = 0; i < 2; i++) {
            treasureCards.add(new JButton());
            treasureCards.get(i).setPreferredSize(treasureCardSize);
            treasureCardPile.add(treasureCards.get(i));
        }

        treasurePanel.add(treasureCardPile, BorderLayout.NORTH);
        treasurePanel.add(waterMeter, BorderLayout.CENTER);
    }

    public JPanel getTreasurePanel() {
        return treasurePanel;
    }
}
