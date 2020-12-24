package com.esr.gui.updater;

import javax.swing.*;

/**
 * @Description
 * @Author William
 * @Date 2020/11/20
 * @Version 1.0
 **/
public class LogAgent {
    public static JTextArea logs = new JTextArea();

    public static void logMessenger(String log) {
        logs.append(log + '\n');
    }
}
