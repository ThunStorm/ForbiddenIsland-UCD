package com.esr.gui.updater;

import javax.swing.*;

/**
 * @Description
 * @Author William
 * @Date 2020/11/20
 * @Version 1.0
 **/
public class LogAgent {
    private JTextArea logs;

    public LogAgent() {
        this.logs = new JTextArea();
    }

    public JTextArea getLogs() {
        return logs;
    }

    public void logMessenger(String log){
        logs.append(log);
    }
}
