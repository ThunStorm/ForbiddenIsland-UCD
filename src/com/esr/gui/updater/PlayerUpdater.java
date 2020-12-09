package com.esr.gui.updater;

import com.esr.gui.game.GamePanel;
import com.esr.service.base.IUpdater;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class PlayerUpdater implements IUpdater {

    public PlayerUpdater(ArrayList<String> pawnImg){
        for (int i = 0; i < pawnImg.size(); i++) {
            GamePanel.playerPawnList.get(i).setIcon(new ImageIcon(CommonUtils.getImage(pawnImg.get(i))));
        }
    }

    @Override
    public void guiUpdate(ArrayList<String> img) {

    }

    public void handCardUpdate(ArrayList<JButton> handCards, ArrayList<Integer> img){
        for (int i = 0; i < img.size(); i++) {
            handCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("/TreasureCards/" + img.get(i) + ".png", Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT)));
            handCards.get(i).setAlignmentX(SwingConstants.CENTER);
        }
    }
}





//    public void setUpPlayer(int idxOfAdventurers) {
//        pawn.setIcon(new ImageIcon(CommonUtils.getImage("Pawns/" + adventurers.get(idxOfAdventurers).getName() + ".png")));
////            pawn.setIcon(null);
////        System.out.println(pawn.getIcon());
//        for (int i = 0; i < 5; i++) {
//            handCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("HandCards/" + ((int) (Math.random() * 6)) + ".png", Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT)));
////                handCards.get(i).setAlignmentX(SwingConstants.CENTER);
//        }
//    }
//

