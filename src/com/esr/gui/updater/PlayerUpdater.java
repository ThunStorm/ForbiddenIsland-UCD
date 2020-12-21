package com.esr.gui.updater;

import com.esr.gui.game.BoardPanel;
import com.esr.gui.game.GamePanel;
import com.esr.gui.widgets.TwoLayeredIcon;
import com.esr.service.base.IUpdater;
import com.esr.service.game.Game;
import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.Adventurer;
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
public class PlayerUpdater implements IUpdater {

    public PlayerUpdater(){
        for (int i = 0; i < Game.getNumOfPlayer(); i++) {
            GamePanel.playerPawnList.get(i).setIcon(new ImageIcon(CommonUtils.getImage(GameData.getAdventurers()[i].getPawnImg())));
            for (int j = 0; j < GameData.getAdventurers()[i].getHandCards().size(); j++){
                GamePanel.playerHandCards.get(i).get(j).setIcon(new ImageIcon(CommonUtils.getImage("/TreasureCards/" + GameData.getAdventurers()[i].getHandCards().get(j) + ".png", Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT)));
                GamePanel.playerHandCards.get(i).get(j).setAlignmentX(SwingConstants.CENTER);
            }
        }
    }

    @Override
    public void guiUpdate() {
        for (int i = 0; i < Game.getNumOfPlayer(); i++) {
            for (int k = 0; k < GameData.getAdventurers()[i].getCapturedFigurines().size(); k++){
                GamePanel.playerPawnList.get(i).setIcon(
                        new TwoLayeredIcon( new ImageIcon(CommonUtils.getImage("/Figurines/"
                                + GameData.getAdventurers()[i].getCapturedFigurines().get(k).name() + ".png")),
                                GamePanel.playerPawnList.get(i).getIcon()));
            }
            for (int j = 0; j < GameData.getAdventurers()[i].getHandCards().size(); j++){
                GamePanel.playerHandCards.get(i).get(j).setIcon(new ImageIcon(CommonUtils.getImage("/TreasureCards/"
                        + GameData.getAdventurers()[i].getHandCards().get(j) + ".png", Constant.ADVENTURER_WIDTH, Constant.ADVENTURER_HEIGHT)));
                GamePanel.playerHandCards.get(i).get(j).setAlignmentX(SwingConstants.CENTER);
            }
            for (int j = GameData.getAdventurers()[i].getHandCards().size(); j < GamePanel.playerHandCards.get(i).size(); j++){
                GamePanel.playerHandCards.get(i).get(j).setIcon(null);
                GamePanel.playerHandCards.get(i).get(j).setAlignmentX(SwingConstants.CENTER);
            }
        }
    }
}
