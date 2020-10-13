package com.esr.gui;

import com.esr.utils.Audio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ConsolePanel {
    private Box consolePanel = Box.createVerticalBox();
    private JPanel infoPanel = new JPanel();
    private Box configBox = Box.createVerticalBox();
    private Box logBox = Box.createHorizontalBox();
    private JPanel actionPanel = new JPanel();
    private JTextArea logs = new JTextArea();
    private JButton moveTo = new JButton("Move To");
    private JButton liftOff = new JButton("Lift Off");
    private JButton shoreUp = new JButton("Shore Up");
    private JButton passTo = new JButton("Pass To");
    private JButton capture = new JButton("Capture");
    private JButton nextStep = new JButton("Next");
    private JButton clearSlt = new JButton("Clear");
    private JButton startGame = new JButton("Start");
//    private int numOfPlayers;


    public ConsolePanel() {
        InfoPanel();
        ConfigBox();
        LogBox();
        ActionPanel();
        consolePanel.add(infoPanel);
        consolePanel.add(Box.createVerticalStrut(5));
        consolePanel.add(configBox);
        consolePanel.add(Box.createVerticalStrut(15));
        consolePanel.add(logBox);
        consolePanel.add(actionPanel);
        consolePanel.add(Box.createVerticalGlue());
        consolePanel.setPreferredSize(new Dimension(95, 950));
    }

    public Box getConsolePanel() {
        return consolePanel;
    }

    public void updateLogs(String string) {
        logs.append(string);
    }

    public void InfoPanel() {
        TitledBorder info = new TitledBorder("Info");
        JLabel INFO1 = new JLabel();
        INFO1.setFont(new Font(null, Font.BOLD, 13));
        INFO1.setText("<html>FORBIDDEN<br>&nbsp;&nbsp;&nbsp;ISLAND<br></html>");
        JLabel INFO2 = new JLabel();
        INFO2.setFont(new Font(null, Font.BOLD, 10));
        INFO2.setText("<html>CREATED BY:<br>ESR TEAM</html>");
        infoPanel.add(INFO1);
        infoPanel.add(INFO2);
        infoPanel.setBorder(info);
        infoPanel.setPreferredSize(new Dimension(95, 100));
    }


    public void ConfigBox() {
        Box keyBox = Box.createHorizontalBox();
        TitledBorder config = new TitledBorder("Config");

        JComboBox<Object> numOfPlayerCB = new JComboBox<>();
        numOfPlayerCB.addItem("----Num----");
        for (int i = 2; i < 5; i++) {
            numOfPlayerCB.addItem(Integer.toString(i));
        }
//        if (numOfPlayerCB.getSelectedItem() != "----Num----") {
//            numOfPlayers = Integer.valueOf((Integer) numOfPlayerCB.getSelectedItem());
//        }
        startGame.addActionListener(e -> {
            if (numOfPlayerCB.getSelectedItem() != "----Num----") {
//                System.out.println(numOfPlayerCB.getSelectedItem());
                updateLogs("abcdefg hijklmn opq rst uvw xyz\n");
                Audio.BGM.LoopPlay();
                startGame.setEnabled(false);
                ///////////////////////////////////////////////////
            }
        });

        keyBox.add(Box.createHorizontalGlue());
        keyBox.add(startGame);
        configBox.add(numOfPlayerCB);
        configBox.add(Box.createVerticalStrut(2));
        configBox.add(keyBox);
        configBox.setBorder(config);
        configBox.setPreferredSize(new Dimension(95, 80));

    }

    public void LogBox() {
        logs.setLineWrap(true);
        logs.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(logs);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        TitledBorder logBorder = new TitledBorder("Logs");
        logBox.add(scrollPane);
        logBox.setBorder(logBorder);
        logBox.setPreferredSize(new Dimension(95, 570));
    }

    public void ActionPanel() {
        TitledBorder action = new TitledBorder("Action");
        GridLayout gridLayout = new GridLayout(7, 1, 0, 2);
        actionPanel.setLayout(gridLayout);
        actionPanel.add(moveTo);
        actionPanel.add(liftOff);
        actionPanel.add(shoreUp);
        actionPanel.add(passTo);
        actionPanel.add(capture);
        actionPanel.add(nextStep);
        actionPanel.add(clearSlt);
        actionPanel.setPreferredSize(new Dimension(95, 180));
    }
    //    public JButton getStartButton() {
//        return startGame;
//    }
//
//    public int getNumOfPlayerCB() {
//        return numOfPlayers;
//    }
}
