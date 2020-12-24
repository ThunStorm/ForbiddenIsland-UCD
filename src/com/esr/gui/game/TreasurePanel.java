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
public class TreasurePanel {
    public static JLabel waterMeter = new JLabel();
    public static ArrayList<JButton> treasureCards;
    private final JPanel treasurePanel;

    // the panel used to display treasure cards
    public TreasurePanel() {
        treasurePanel = new JPanel(new BorderLayout(1, 3));
        JPanel treasureCardPile = new JPanel(new GridLayout(4, 1, 1, 3));
        treasureCards = new ArrayList<>();

        JLabel pile = new JLabel();
        Dimension treasureCardSize = new Dimension(Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT);
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
            treasureCards.get(i).setEnabled(false);
            treasureCardPile.add(treasureCards.get(i));
        }

        treasurePanel.add(treasureCardPile, BorderLayout.NORTH);
        treasurePanel.add(waterMeter, BorderLayout.CENTER);
    }

    public JPanel getTreasurePanel() {
        return treasurePanel;
    }
}
