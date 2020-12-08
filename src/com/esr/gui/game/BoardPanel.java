package com.esr.gui.game;

import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.gui.widgets.JPanelBG;
import com.esr.gui.widgets.TwoLayeredIcon;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;
import com.esr.utils.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BoardPanel {

    private JPanelBG board;
//    private int[] layOut = new int[]{1, 2, 5, 6, 7, 12, 25, 30, 31, 32, 35, 36};
    private final Dimension tileSize = new Dimension(Constant.TILE_WIDTH, Constant.TILE_HEIGHT);
    private final Dimension boardSize = new Dimension(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
    private ArrayList<JButton> tileCards = new ArrayList<>();
//    private HashMap<String, JButton> tileCards;

    public BoardPanel() {
        board = new JPanelBG(Constant.RESOURCES_PATH + "Map/Arena.jpg", new GridLayout(6, 6, 2, 2));
//        BoardBTN();
    }

//    // interface reserved for setting up new layout of the game board
//    public BoardPanel(int[] layOut) {
//        this.layOut = layOut;
//        initBG();
////        BoardBTN();
//    }

    public void setBoardBTN(ArrayList<Integer> tileOrder, ArrayList<Adventurer> adventurers) {
        ArrayList<Integer> blank = new ArrayList<>();
        for (int j : Map.layOut) {
            blank.add(j - 1);
        }

        Iterator<Integer> iterator = tileOrder.iterator();
        for (int i = 0; i < 36; i++) {
            JButton tileCard = new JButton();
            tileCard.setPreferredSize(tileSize);
            if (blank.contains(i)) {
                tileCard.setVisible(false);
            }
            else {
                if (iterator.hasNext()){
                    int itr = iterator.next();
//                    System.out.println(itr);
                    tileCard.setIcon(new ImageIcon(CommonUtils.getImage("/Tiles/" + itr + ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)));
                    for (Adventurer adventurer : adventurers) {
                        if((adventurer.getId() + 8) == itr){
//                            System.out.println("Name: "+adventurer.getName()+", pos: "+adventurer.getPos()+", ITR: "+itr);
                            tileCard.setIcon(new TwoLayeredIcon(new ImageIcon(CommonUtils.getImage("/Pawns/" + adventurer.getName()+ ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)), tileCard.getIcon()));
                        }
                    }
                    tileCards.add(tileCard);
                }
                else{
                    System.out.println("Error and later program this exception part");
                }
            }
            board.add(tileCard);
        }
        board.setPreferredSize(boardSize);
    }

    public JPanelBG getBoard() {
        return board;
    }

    public ArrayList<JButton> getTileCards(){
        return tileCards;
    }

}
