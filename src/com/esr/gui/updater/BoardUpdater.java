package com.esr.gui.updater;

import com.esr.gui.game.BoardPanel;
import com.esr.gui.tools.TwoLayeredIcon;
import com.esr.service.base.IUpdater;
import com.esr.service.game.GameData;
import com.esr.service.game.data.Block;
import com.esr.utils.CommonUtils;
import com.esr.utils.Constant;
import com.esr.utils.Map;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class BoardUpdater implements IUpdater {

    public BoardUpdater() {
        ArrayList<Integer> tiles = new ArrayList<>(GameData.getTilesArray());
        for (int i = 0; i < BoardPanel.tileCards.size(); i++) {
            BoardPanel.tileCards.get(i).setEnabled(true);
            BoardPanel.tileCards.get(i).setVisible(true);
            BoardPanel.tileCards.get(i).setIcon(new ImageIcon(CommonUtils.getImage("/Tiles/" + tiles.get(i) + ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)));
        }
        for (int i = 0; i < GameData.getAdventurers().length; i++) {
            BoardPanel.tileCards.get(GameData.getAdventurers()[i].getPos()).setIcon(new TwoLayeredIcon(new ImageIcon(CommonUtils.getImage(GameData.getAdventurers()[i].getPawnImg(), Constant.TILE_WIDTH, Constant.TILE_HEIGHT)), BoardPanel.tileCards.get(tiles.indexOf(GameData.getAdventurers()[i].getId() + 9)).getIcon()));
        }
    }

    @Override
    public void guiUpdate() {
        int idx = 0;
        for (Block[] blocks : GameData.getBoard().getTileMap()) {
            for (Block block : blocks) {
                if (block.isExist()) {
                    BoardPanel.tileCards.get(idx).setIcon(new ImageIcon(CommonUtils.getImage(block.getImg(), Constant.TILE_WIDTH, Constant.TILE_HEIGHT)));
                } else if (!block.isExist() && (block.getTileId() != -1)) {
                    BoardPanel.tileCards.get(idx).setVisible(false);
                    BoardPanel.tileCards.get(idx).setEnabled(false);
                } else {
                    idx--;
                }
                idx++;
            }
        }
        for (int i = 0; i < GameData.getAdventurers().length; i++) {

            for (int j = 0; j < GameData.getBoard().getTile(GameData.getAdventurers()[i].getX(), GameData.getAdventurers()[i].getY()).getPlayerOnBoard().size(); j++) {
                BoardPanel.tileCards.get(GameData.getAdventurers()[i].getPos()).setIcon(
                        new TwoLayeredIcon(new ImageIcon(CommonUtils.getImage("/Pawns/"
                                + Map.adventurerMatcher.get(GameData.getBoard().getTile(GameData.getAdventurers()[i].getX(), GameData.getAdventurers()[i].getY()).getPlayerOnBoard().get(j))
                                + ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)),
                                BoardPanel.tileCards.get(GameData.getAdventurers()[i].getPos()).getIcon(), j));
            }
        }
    }

    @Override
    public void gameOver() {
        for (JButton tile : BoardPanel.tileCards) {
            tile.setEnabled(false);
        }
    }

}

