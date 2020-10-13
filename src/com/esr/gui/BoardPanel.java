package com.esr.gui;

import com.esr.gui.widgets.JPanelBG;
import com.esr.gui.widgets.TwoLayeredIcon;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel {

    private JPanelBG board;
    private int[] layOut = new int[]{1, 2, 5, 6, 7, 12, 25, 30, 31, 32, 35, 36};
    private final Dimension tileSize = new Dimension(Constant.TILE_WIDTH, Constant.TILE_HEIGHT);
    private final Dimension boardSize = new Dimension(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
    private ArrayList<JButton> tileCards;

    public BoardPanel() {
        initBG();
//        BoardBTN();
    }

    // interface reserved for setting up new layout of the game board
    public BoardPanel(int[] layOut) {
        this.layOut = layOut;
        initBG();
//        BoardBTN();
    }

    private void initBG() {
        board = new JPanelBG(Constant.RESOURCES_PATH + "Map/Arena.jpg", new GridLayout(6, 6, 2, 2));
        //code to set layout
    }

    public void setBoardBTN() {
        ArrayList<Integer> blank = new ArrayList<>();
        for (int j : layOut) {
            blank.add(j - 1);
        }
        tileCards = new ArrayList<>();
        int cardIndex = 1;
        for (int i = 0; i < 36; i++) {
            JButton tileCard = new JButton();
            tileCard.setPreferredSize(tileSize);
            if (blank.contains(i)) {
                tileCard.setVisible(false);
            } else {
                tileCard.setIcon(new ImageIcon(CommonUtils.getImage("/Tiles/" + cardIndex + ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)));
                tileCards.add(tileCard);
                cardIndex++;
            }
            board.add(tileCard);

        }
//        System.out.println(tileCards.get(5).getIcon());
        tileCards.get(3).setIcon(new TwoLayeredIcon(new ImageIcon(CommonUtils.getImage("/Pawns/" + "Navigator.png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)), tileCards.get(3).getIcon()));
        board.setPreferredSize(boardSize);
    }

    public JPanelBG getBoard() {
        return board;
    }

    public ArrayList<JButton> getTileCards() {
        return tileCards;
    }

}
