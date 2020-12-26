package com.esr.gui;

import com.esr.gui.console.ConsolePanel;
import com.esr.gui.game.GamePanel;
import com.esr.gui.updater.LogAgent;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {

    public GameFrame(String title) throws HeadlessException {
        super(title);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int screenWidth = toolkit.getScreenSize().width;
        int screenHeight = toolkit.getScreenSize().height;
        this.setResizable(false);
        this.setLayout(new BorderLayout(5, 5));
        this.setBounds((screenWidth - Constant.FRAME_WIDTH) / 2, (screenHeight - Constant.FRAME_HEIGHT) / 2, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        Init();
    }

    public void Init() {
        ConsolePanel consolePanel = new ConsolePanel();
        GamePanel gamePanel = new GamePanel();
        JPanel jGamePanel = gamePanel.getGamePanel();
        Box consoleBoxPanel = consolePanel.getConsolePanel();
        this.add(consoleBoxPanel, BorderLayout.EAST);
        this.add(jGamePanel, BorderLayout.CENTER);
    }

}
