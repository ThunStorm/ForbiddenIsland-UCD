package com.esr.gui.updater;

import com.esr.gui.console.ConsolePanel;
import com.esr.gui.game.TreasurePanel;
import com.esr.service.base.IUpdater;
import com.esr.service.game.GameData;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;

import javax.swing.*;

/**
 * @Description
 * @Author William
 * @Date 2020/12/17
 * @Version 1.0
 **/
public class WaterMeterUpdater implements IUpdater {
    public WaterMeterUpdater() {
        TreasurePanel.waterMeter.setIcon(new ImageIcon(CommonUtils.getImage(GameData.getWaterMeterImg(), Constant.WATER_METER_WIDTH, Constant.WATER_METER_HEIGHT)));
    }

    @Override
    public void guiUpdate() {
        TreasurePanel.waterMeter.setIcon(new ImageIcon(CommonUtils.getImage(GameData.getWaterMeterImg(), Constant.WATER_METER_WIDTH, Constant.WATER_METER_HEIGHT)));
    }

    @Override
    public void gameOver() {
        TreasurePanel.waterMeter.setVisible(false);
    }
}
