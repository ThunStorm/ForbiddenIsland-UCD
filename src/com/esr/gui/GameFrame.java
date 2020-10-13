package com.esr.gui;

import com.esr.service.observer.Subject;
import com.esr.utils.Constant;

import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {

//    public Subject subject;
    private GamePanel gamePanel;
    private ConsolePanel consolePanel;
    private JPanel jGamePanel;
    private Box consoleBoxPanel;


    public GameFrame(String title) throws HeadlessException {
        super(title);
        //Dimension frameSize = new Dimension(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int screenWidth = toolkit.getScreenSize().width;
        int screenHeight = toolkit.getScreenSize().height;
        this.setResizable(false);

        this.setLayout(new BorderLayout(5, 5));
        this.setBounds((screenWidth - Constant.FRAME_WIDTH) / 2, (screenHeight - Constant.FRAME_HEIGHT) / 2, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        Init();
//        test();
//        System.out.println(this.getBounds());

    }

    public void Init() {
        gamePanel = new GamePanel();
        consolePanel = new ConsolePanel();
        jGamePanel = gamePanel.getGamePanel();
        consoleBoxPanel = consolePanel.getConsolePanel();
        this.add(consoleBoxPanel, BorderLayout.EAST);
        this.add(jGamePanel, BorderLayout.CENTER);
        //the size AudioData object is limited under 1 MB when looped play;
//        Audio.BGM.LoopPlay();
    }

//    public void test() {
//        JButton startGame = consolePanel.getStartButton();
//        int numOfPlayer = consolePanel.getNumOfPlayerCB();
//        startGame.addActionListener(e -> {
////                logs.append("abcdefg hijklmn opq rst uvw xyz\n");
////                Audio.BGM.LoopPlay();
//            startGame.setEnabled(false);
////                subject.notifyAllObservers();
//            gamePanel.update();
//        });
//    }
}
