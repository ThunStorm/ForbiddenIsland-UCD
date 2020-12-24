package com.esr.gui.game;

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
public class PlayerPanel {

    Player player1;
    Player player2;
    private final Dimension adventurerSize = new Dimension(Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT);
    private final Box duoPlayerPanel = Box.createHorizontalBox();

    // this panel is designed for player's pawn and hands interface
    public PlayerPanel() {
        player1 = new Player();
        player2 = new Player();
        duoPlayerPanel.add(player1.playerPanel);
        duoPlayerPanel.add(Box.createHorizontalStrut(10));
        duoPlayerPanel.add(player2.playerPanel);
    }

    public JButton getP1Pawn() {
        return player1.pawn;
    }

    public ArrayList<JButton> getP1HandCards() {
        return player1.handCards;
    }

    public JButton getP2Pawn() {
        return player2.pawn;
    }

    public ArrayList<JButton> getP2HandCards() {
        return player2.handCards;
    }

    public Box getDuoPlayerPanel() {
        return duoPlayerPanel;
    }


    public class Player {
        private final JPanel playerPanel = new JPanel(new BorderLayout(3, 0));
        private final JButton pawn;
        private final ArrayList<JButton> handCards = new ArrayList<>();

        public Player() {
            pawn = new JButton();
            pawn.setPreferredSize(adventurerSize);
            playerPanel.add(pawn, BorderLayout.WEST);
            JPanel handCardPanel = new JPanel(new GridLayout(1, 5, 1, 1));
            for (int i = 0; i < 5; i++) {
                handCards.add(new JButton());
                handCards.get(i).setPreferredSize(adventurerSize);
                handCards.get(i).setEnabled(false);
                handCardPanel.add(handCards.get(i));
            }
            playerPanel.add(handCardPanel, BorderLayout.CENTER);
            playerPanel.setPreferredSize(new Dimension(440, 120));
        }
    }

}
