package com.esr.gui.updater;

import com.esr.gui.game.TreasurePanel;
import com.esr.service.base.IUpdater;
import com.esr.service.game.GameData;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class TreasureUpdater implements IUpdater {
    @Override
    public void guiUpdate() {
        TreasurePanel.treasureCards.get(0).setIcon(new ImageIcon((CommonUtils.getImage("/TreasureCards/"
                + GameData.getTreasureDeck().getNTreasureCards(2).get(0) + ".png", Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT, 270d))));
        TreasurePanel.treasureCards.get(1).setIcon(new ImageIcon((CommonUtils.getImage("/TreasureCards/"
                + GameData.getTreasureDeck().getNTreasureCards(2).get(1) + ".png", Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT, 270d))));
    }
}
