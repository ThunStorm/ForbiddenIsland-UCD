package com.esr.gui.updater;

import com.esr.gui.game.BoardPanel;
import com.esr.gui.widgets.TwoLayeredIcon;
import com.esr.service.base.IUpdater;
import com.esr.service.game.GameData;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;
import com.esr.utils.Map;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class BoardUpdater implements IUpdater {

    public BoardUpdater(ArrayList<Integer> img) {
        for (int i = 0; i < BoardPanel.tileCards.size(); i++) {
            BoardPanel.tileCards.get(i).setEnabled(true);
            BoardPanel.tileCards.get(i).setVisible(true);
            BoardPanel.tileCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("/Tiles/" + img.get(i) + ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)));
        }
        for (int i = 0; i < GameData.getAdventurers().length; i++) {
            BoardPanel.tileCards.get(GameData.getAdventurers()[i].getPos()).setIcon(new TwoLayeredIcon(new ImageIcon(CommonUtils.getImage("/Pawns/" + GameData.getAdventurers()[i].getName() + ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)), BoardPanel.tileCards.get(GameData.getTilesArray().indexOf(GameData.getAdventurers()[i].getId()+8)).getIcon()));
        }
    }

    @Override
    public void guiUpdate(ArrayList<Integer> img) {
        //need to consider the length change later, move this to constructor
//        System.out.println(img);
//        for (int i = 0; i < BoardPanel.tileCards.size(); i++) {
//            BoardPanel.tileCards.get(i).setEnabled(true);
//            BoardPanel.tileCards.get(i).setVisible(true);
//            BoardPanel.tileCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("/Tiles/" + img.get(i) + ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)));
//        }
    }
}

