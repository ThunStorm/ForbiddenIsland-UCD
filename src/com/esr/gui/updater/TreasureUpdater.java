package com.esr.gui.updater;

import com.esr.gui.game.TreasurePanel;
import com.esr.service.base.IUpdater;
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
    public void guiUpdate(ArrayList<String> img) {
        for (int i = 0; i < 2; i++) {
            TreasurePanel.treasureCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage(img.get(i), Constant.TREASURE_WIDTH, Constant.TREASURE_HEIGHT, 270d)));
        }
    }
}
