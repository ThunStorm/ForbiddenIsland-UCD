package com.esr;

import com.esr.gui.GameFrame;

import javax.swing.*;

/**
 * @Description
 * @Author William & PJW
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class Main {

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame("Forbidden Island");
//        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}