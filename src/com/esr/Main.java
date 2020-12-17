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
//        Subject subject = new Subject();
        GameFrame gameFrame = new GameFrame("Forbidden Island");
//        game_frame gameFrame = new game_frame("Forbidden Island");
//        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

//TODO Remove problem [GAME, CONTROLLER-CAPTURE, BLOCK-SINK-CHECK-WIN] [NEW CHARACTER IMPLEMENT] [LOSE CONDITION NO TILE TO SWIM TO] [SPECIAL ACTIONS] [PASS CARD TO FULL CARD ONES]