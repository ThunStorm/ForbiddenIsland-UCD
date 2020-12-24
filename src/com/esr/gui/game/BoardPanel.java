package com.esr.gui.game;

import com.esr.gui.tools.JPanelBG;
import com.esr.utils.Constant;
import com.esr.utils.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class BoardPanel {
    public static ArrayList<JButton> tileCards = new ArrayList<>();
    private final JPanelBG board;

    // create a chessboard panel
    public BoardPanel() {
        Dimension tileSize = new Dimension(Constant.TILE_WIDTH, Constant.TILE_HEIGHT);
        Dimension boardSize = new Dimension(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
        board = new JPanelBG(Constant.RESOURCES_PATH + "Map/Arena.jpg", new GridLayout(6, 6, 2, 2));
        for (int i = 0; i < 36; i++) {
            JButton tileCard = new JButton();
            tileCard.setPreferredSize(tileSize);
            if (Map.blankLayout.contains(i)) {
                tileCard.setVisible(false);
            } else {
                tileCard.setVisible(false);
                tileCards.add(tileCard);
            }
            board.add(tileCard);
        }
        board.setPreferredSize(boardSize);
    }

    public JPanelBG getBoard() {
        return board;
    }

}
