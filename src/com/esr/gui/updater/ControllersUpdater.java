package com.esr.gui.updater;

import com.esr.gui.console.ConsolePanel;
import com.esr.service.base.IUpdater;
import com.esr.service.game.Game;

import javax.swing.*;

/**
 * @Description
 * @Author William
 * @Date 2020/12/24
 * @Version 1.0
 **/
public class ControllersUpdater implements IUpdater {
    @Override
    public void guiUpdate() {
        if (Game.isInFakeRound()){
            for (JButton controller : ConsolePanel.consoleButtons){
                controller.setEnabled(!Game.isNeed2save());
            }
            ConsolePanel.consoleButtons.get(1).setEnabled(Game.isNeed2save());
        }
        else {
            for (JButton controller : ConsolePanel.consoleButtons){
                controller.setEnabled(!Game.isNeed2save());
            }
        }

    }

    @Override
    public void gameOver() {
        for (JButton controller : ConsolePanel.consoleButtons){
            controller.setEnabled(false);
        }
    }
}
