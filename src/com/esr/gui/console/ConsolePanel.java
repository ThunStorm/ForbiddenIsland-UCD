package com.esr.gui.console;

import com.esr.gui.updater.LogAgent;
import com.esr.service.game.Game;
import com.esr.utils.Audio;
import com.esr.utils.Constant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ConsolePanel {
    public static ArrayList<JButton> consoleButtons = new ArrayList<>();
    public static JComboBox<Object> numOfPlayerCB = new JComboBox<>();
    public static JComboBox<Object> difficultyCB = new JComboBox<>();

    private Box consolePanel = Box.createVerticalBox();
    private JPanel infoPanel = new JPanel();
    private Box configBox = Box.createVerticalBox();
    private Box logBox = Box.createHorizontalBox();
    private JPanel actionPanel = new JPanel();
    private JTextArea logs;

    public ConsolePanel() {
        this.logs = LogAgent.logs;
        consoleButtons.add(new JButton("Start"));
        consoleButtons.add(new JButton("Move To"));
        consoleButtons.add(new JButton("Shore Up"));
        consoleButtons.add(new JButton("Pass To"));
        consoleButtons.add(new JButton("Capture"));
        consoleButtons.add(new JButton("Lift Off"));
        consoleButtons.add(new JButton("Special Actions"));
        consoleButtons.add(new JButton("Next"));
        consoleButtons.add(new JButton("Discard"));
        consoleButtons.add(new JButton("Clear"));
        InfoPanel();
        ConfigBox();
        LogBox();
        ActionPanel();
        consolePanel.add(infoPanel);
        consolePanel.add(Box.createVerticalStrut(5));
        consolePanel.add(configBox);
        consolePanel.add(Box.createVerticalStrut(5));
        consolePanel.add(logBox);
        consolePanel.add(actionPanel);
        consolePanel.add(Box.createVerticalGlue());
        consolePanel.setPreferredSize(new Dimension(Constant.CONSOLE_WIDTH, Constant.CONSOLE_HEIGHT));
    }

    public void ConfigBox() {
        Box keyBox = Box.createHorizontalBox();
        TitledBorder config = new TitledBorder("Config");

        numOfPlayerCB.addItem("----NUM----");
        for (int i = 2; i < 5; i++) {
            numOfPlayerCB.addItem(Integer.toString(i));
        }

        difficultyCB.addItem("---LEVEL---");
        for (int i = 1; i < 6; i++) {
            difficultyCB.addItem(Integer.toString(i));
        }
        consoleButtons.get(0).addActionListener(e -> {
            if (numOfPlayerCB.getSelectedItem() != "----NUM----" && difficultyCB.getSelectedItem() != "---LEVEL---") {
                LogAgent.logMessenger("[Game Initialisation]");
                if (Constant.AUDIO_ON_OFF) {
                    Audio.BGM.LoopPlay(398);
                }
                consoleButtons.get(0).setEnabled(false);
                Game NewGame = new Game(Integer.parseInt((String) Objects.requireNonNull(numOfPlayerCB.getSelectedItem())),
                        Integer.parseInt((String) Objects.requireNonNull(difficultyCB.getSelectedItem())));
            }
        });

        keyBox.add(Box.createHorizontalGlue());
        keyBox.add(consoleButtons.get(0));
        configBox.add(numOfPlayerCB);
        configBox.add(difficultyCB);
        configBox.add(Box.createVerticalStrut(2));
        configBox.add(keyBox);
        configBox.setBorder(config);
        configBox.setPreferredSize(new Dimension(95, 120));

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
        logBox.setPreferredSize(new Dimension(95, 500));
    }

    public void ActionPanel() {
        TitledBorder action = new TitledBorder("Action");
        GridLayout gridLayout = new GridLayout(9, 1, 0, 2);
        actionPanel.setLayout(gridLayout);
        actionPanel.add(consoleButtons.get(1));
        actionPanel.add(consoleButtons.get(2));
        actionPanel.add(consoleButtons.get(3));
        actionPanel.add(consoleButtons.get(4));
        actionPanel.add(consoleButtons.get(5));
        actionPanel.add(consoleButtons.get(6));
        actionPanel.add(consoleButtons.get(7));
        actionPanel.add(consoleButtons.get(8));
        actionPanel.add(consoleButtons.get(9));
        actionPanel.setPreferredSize(new Dimension(95, 220));
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

    public Box getConsolePanel() {
        return consolePanel;
    }

}
