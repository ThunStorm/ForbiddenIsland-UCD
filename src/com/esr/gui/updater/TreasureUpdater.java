package com.esr.gui.updater;

import com.esr.gui.game.TreasurePanel;
import com.esr.service.base.IUpdater;
import com.esr.service.game.GameData;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class TreasureUpdater implements IUpdater {
    public TreasureUpdater() {
        for (JButton treasureCard : TreasurePanel.treasureCards) {
            treasureCard.setEnabled(false);
        }
    }

    @Override
    public void guiUpdate() {
        for (int i = 0; i < GameData.getDisplayedTreasureCard().size(); i++) {
            TreasurePanel.treasureCards.get(i).setEnabled(true);
            TreasurePanel.treasureCards.get(i).setIcon(new ImageIcon((CommonUtils.getImage("/TreasureCards/"
                    + GameData.getDisplayedTreasureCard().get(i) + ".png", Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT, 270d))));
        }
        for (int i = GameData.getDisplayedTreasureCard().size(); i < TreasurePanel.treasureCards.size(); i++) {
            TreasurePanel.treasureCards.get(i).setIcon(null);
            TreasurePanel.treasureCards.get(i).setEnabled(false);
        }
    }

    @Override
    public void gameOver() {
        for (JButton treasureCard : TreasurePanel.treasureCards) {
            treasureCard.setEnabled(false);
        }
    }
}
