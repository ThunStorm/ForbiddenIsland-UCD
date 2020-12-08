package com.esr.gui.updater;

import javax.swing.*;

/**
 * @Description
 * @Author William
 * @Date 2020/11/20
 * @Version 1.0
 **/
public class LogAgent {
    private static JTextArea logs;

    public LogAgent() {
        logs = new JTextArea();
    }

    public JTextArea getLogs() {
        return logs;
    }

    public static void logMessenger(String log){
        logs.append(log);
    }
}
