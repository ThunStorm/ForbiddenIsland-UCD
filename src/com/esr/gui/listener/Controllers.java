package com.esr.gui.listener;

import com.esr.gui.console.ConsolePanel;
import com.esr.gui.updater.LogAgent;

/**
 * @Description
 * @Author PJW
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class Controllers {
    public Controllers() {
        MoveToController();
        LiftOffController();
        ShoreUpController();
        PassToController();
        CaptureController();
        NextController();
        ClearController();
    }

    private void MoveToController(){
        ConsolePanel.consoleButtons.get(1).addActionListener(e -> {
            LogAgent.logMessenger("Move To");
        });
    }

    private void LiftOffController(){
        ConsolePanel.consoleButtons.get(2).addActionListener(e -> {
            LogAgent.logMessenger("Lift Off");
        });
    }

    private void ShoreUpController(){
        ConsolePanel.consoleButtons.get(3).addActionListener(e -> {
            LogAgent.logMessenger("Shore Up");
        });
    }

    private void PassToController(){
        ConsolePanel.consoleButtons.get(4).addActionListener(e -> {
            LogAgent.logMessenger("Pass To");
        });
    }

    private void CaptureController(){
        ConsolePanel.consoleButtons.get(5).addActionListener(e -> {
            LogAgent.logMessenger("Capture");
        });
    }

    private void NextController(){
        ConsolePanel.consoleButtons.get(6).addActionListener(e -> {
            LogAgent.logMessenger("Next");
        });
    }

    private void ClearController(){
        ConsolePanel.consoleButtons.get(7).addActionListener(e -> {
            LogAgent.logMessenger("Clear");
        });
    }

}
