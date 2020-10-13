package com.esr;

import com.esr.gui.GameFrame;
import com.esr.service.observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        Subject subject = new Subject();
        GameFrame gameFrame = new GameFrame("Forbidden Island");
//        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
