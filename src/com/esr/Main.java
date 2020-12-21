package com.esr;

import com.esr.gui.GameFrame;

import javax.swing.*;
import java.io.IOException;

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

//TODO [LOSE CONDITION NO TILE TO SWIM TO]
// [FLOOD CARD INSUFFICIENT]
// [NEW CHARACTER IMPLEMENT]
// [PASS CARD TO FULL CARD ONES]
// [USE SPECIAL ACTION IN OTHER TURN]
// [DISABLE BUTTON]
// [LOGS]
// [GAME, CONTROLLER-CAPTURE, BLOCK-SINK-CHECK-WIN]
//DONE CONTROLLER-147, GAME-71