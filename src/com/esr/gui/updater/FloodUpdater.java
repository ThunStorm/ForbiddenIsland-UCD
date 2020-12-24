package com.esr.gui.updater;

import com.esr.gui.game.FloodPanel;
import com.esr.service.base.IUpdater;
import com.esr.service.game.GameData;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;

/**
 * @Description
 * @Author William
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class FloodUpdater implements IUpdater {
    @Override
    public void guiUpdate() {
        for (int i = 0; i < FloodPanel.floodCards.size(); i++) {
            if (i < GameData.getFloodDeck().getNCards().size()) {
                FloodPanel.floodCards.get(i).setEnabled(true);
                FloodPanel.floodCards.get(i).setVisible(true);
                FloodPanel.floodCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("/Flood/" + GameData.getFloodDeck().getNCards().get(i) + ".png", Constant.FLOOD_WIDTH, Constant.FLOOD_HEIGHT)));
            } else {
                FloodPanel.floodCards.get(i).setEnabled(false);
                FloodPanel.floodCards.get(i).setVisible(false);
            }
        }
    }

    @Override
    public void gameOver() {
        for (JButton floodCard : FloodPanel.floodCards) {
            floodCard.setEnabled(false);
        }
    }
}
