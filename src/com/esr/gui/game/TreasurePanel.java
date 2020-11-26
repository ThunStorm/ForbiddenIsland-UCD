package com.esr.gui.game;

import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TreasurePanel {

    private Dimension treasureCardSize = new Dimension(Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT);
    private JPanel treasureCardPile;
    private JPanel treasurePanel;
    private JLabel waterMeter = new JLabel();
    private ArrayList<JButton> treasureCards;

    public TreasurePanel() {
        setWaterMeter(0);
        initPile();
    }

    private void initPile() {
        treasurePanel = new JPanel(new BorderLayout(1, 3));
        treasureCardPile = new JPanel(new GridLayout(4, 1, 1, 3));
        JLabel pile = new JLabel();
        pile.setPreferredSize(treasureCardSize);
        pile.setIcon(new ImageIcon(CommonUtils.getImage("/Back/Treasure Discard.png", Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT)));
        treasureCardPile.add(pile);
        JLabel back = new JLabel();
        back.setPreferredSize(treasureCardSize);
        back.setIcon(new ImageIcon(CommonUtils.getImage("/Back/Treasure Deck.png", Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT, 90d)));
        treasureCardPile.add(back);
        treasurePanel.add(treasureCardPile, BorderLayout.NORTH);
        treasurePanel.add(waterMeter, BorderLayout.CENTER);
    }

    // override needed to replace icon
    public void SetUpTreasureCard() {
        treasureCards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            treasureCards.add(new JButton());
            treasureCards.get(i).setPreferredSize(treasureCardSize);
            treasureCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("HandCards/" + Integer.toString(i + 1) + ".png", Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT, 270d)));
//            treasureCard.setIcon(new ImageIcon(CommonUtils.getImage("HandCards/25.png",Constant.TREASURE_WIDTH,Constant.TREASURE_HEIGHT)));
            treasureCardPile.add(treasureCards.get(i));
            setWaterMeter(1);

        }
    }

    public void setWaterMeter(int i){
        waterMeter.setIcon(new ImageIcon(CommonUtils.getImage("/WaterMeter/" + i + ".png", Constant.WATER_METER_WIDTH, Constant.WATER_METER_HEIGHT)));
    }

    public JPanel getTreasurePanel() {
        return treasurePanel;
    }

    public ArrayList<JButton> getTreasureCards() {
        return treasureCards;
    }
}
