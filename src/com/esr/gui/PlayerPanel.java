package com.esr.gui;

import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel {

    private Dimension adventureSize = new Dimension(Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT);
    private Box duoPlayerPanel = Box.createHorizontalBox();
    Player player1;
    Player player2;

    public PlayerPanel() {
        player1 = new Player();
        player2 = new Player();
        duoPlayerPanel.add(player1.getPlayer());
        duoPlayerPanel.add(Box.createHorizontalStrut(10));
        duoPlayerPanel.add(player2.getPlayer());
    }
    public void setUpPlayerPanel(){
        player1.setUpPlayer();
        player2.setUpPlayer();
    }


    public Box getDuoPlayerPanel() {
        return duoPlayerPanel;
    }

    public class Player {
        private JPanel playerPanel = new JPanel(new BorderLayout(3, 0));
        private JPanel handCardPanel = new JPanel(new GridLayout(1, 5, 1, 1));
        private JButton pawn;
        private ArrayList<JButton> handCards = new ArrayList<>();

        public Player(){
            pawn = new JButton();
            pawn.setPreferredSize(adventureSize);
            playerPanel.add(pawn, BorderLayout.WEST);
            for (int i = 0; i < 5; i++) {
                handCards.add(new JButton());
                handCards.get(i).setPreferredSize(adventureSize);
                handCardPanel.add(handCards.get(i));
            }
            playerPanel.add(handCardPanel, BorderLayout.CENTER);
            playerPanel.setPreferredSize(new Dimension(440, 120));
        }

        public void setUpPlayer() {
            pawn.setIcon(new ImageIcon(CommonUtils.getImage("Pawns/Engineer.png")));
//        System.out.println(pawn.getIcon());
            for (int i = 0; i < 5; i++) {
                handCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("HandCards/15.png", Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT)));
//                handCards.get(i).setAlignmentX(SwingConstants.CENTER);
            }
        }

        public JPanel getPlayer() {
            return playerPanel;
        }

    }


}
