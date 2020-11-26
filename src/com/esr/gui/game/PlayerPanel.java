package com.esr.gui.game;

import com.esr.adventurer.Adventurer;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel {

    private Dimension adventurerSize = new Dimension(Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT);
    private Box duoPlayerPanel = Box.createHorizontalBox();
    private ArrayList<Adventurer> adventurers;
    private int flag;
    Player player1;
    Player player2;

    public PlayerPanel(ArrayList<Adventurer> adventurers) {
        this.adventurers = adventurers;
        ArrayList<Player> players = new ArrayList<>();
        player1 = new Player();
        player2 = new Player();
        duoPlayerPanel.add(player1.getPlayer());
        duoPlayerPanel.add(Box.createHorizontalStrut(10));
        duoPlayerPanel.add(player2.getPlayer());
    }
    public void setUpPlayerPanel(int Num, int flag){
        this.flag = flag;
        switch (Num){
            case 0:
                break;
            case 1:
                player1.setUpPlayer(flag);
                break;
            case 2:
                player1.setUpPlayer(flag);
                player2.setUpPlayer(flag + 1);
                break;
        }
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
            pawn.setPreferredSize(adventurerSize);
            playerPanel.add(pawn, BorderLayout.WEST);
            for (int i = 0; i < 5; i++) {
                handCards.add(new JButton());
                handCards.get(i).setPreferredSize(adventurerSize);
                handCardPanel.add(handCards.get(i));
            }
            playerPanel.add(handCardPanel, BorderLayout.CENTER);
            playerPanel.setPreferredSize(new Dimension(440, 120));
        }

        public void setUpPlayer(int idxOfAdventurers) {
            pawn.setIcon(new ImageIcon(CommonUtils.getImage("Pawns/" + adventurers.get(idxOfAdventurers).getName()+".png")));
//        System.out.println(pawn.getIcon());
            for (int i = 0; i < 5; i++) {
                handCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("HandCards/"+ ((int) (Math.random() * 6))+".png", Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT)));
//                handCards.get(i).setAlignmentX(SwingConstants.CENTER);
            }
        }

        public JPanel getPlayer() {
            return playerPanel;
        }

    }


}
