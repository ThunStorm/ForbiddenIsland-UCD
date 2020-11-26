package com.esr.gui.updater;

import com.esr.utils.Map;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Description
 * @Author William
 * @Date 2020/11/20
 * @Version 1.0
 **/
public class TileUpdater {
//    protected HashMap<String, JButton> tileCards;
    protected ArrayList<JButton> tileCards;
//    public TileUpdater(HashMap<String, JButton> tileCards) {
    public TileUpdater(ArrayList<JButton> tileCards) {
        this.tileCards = tileCards;
//        System.out.println(this.tileCards);
        for (JButton tileCard:this.tileCards) {
            tileCard.addActionListener(e -> {
                System.out.println("ButtonIDX: " + tileCards.indexOf(tileCard));
                System.out.println("Which matches to " + Arrays.toString(Map.coordinatesMatcher.get(tileCards.indexOf(tileCard))));
                System.out.println("Which inversely matches to " + Map.numberMatcher.get(Arrays.toString(Map.coordinatesMatcher.get(tileCards.indexOf(tileCard)))));
//                System.out.println("tostring" + String.valueOf(new int[]{2, 3}));
            });
        }
//        for (int i = 0; i < Map.rows; i++) {
//            for (int j = 0; j < Map.cols; j++) {
//                tileCards.get(i+","+j).addActionListener(e -> {
//                    System.out.println("ButtonIDX: " );
//                        });
//            }
//        }
    }

}
