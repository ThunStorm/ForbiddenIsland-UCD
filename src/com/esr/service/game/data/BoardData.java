package com.esr.service.game.data;

import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.utils.Map;

import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/14
 * @Version 1.0
 **/
public class BoardData {
    private Blocks[][] tileMap;
    private ArrayList<Integer> tiles;

    public BoardData(ArrayList<Integer> players, ArrayList<Integer> tiles) {
        this.tiles = tiles;
        tileMap = new Blocks[6][6];
        int tileIdx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (Map.blankLayout.contains(i * 6 + j)) {
                    tileMap[i][j] = new Blocks(false);
                }
                else {
                    if (players.contains(this.tiles.get(tileIdx) - 8)){
                        tileMap[i][j] = new Blocks(this.tiles.get(tileIdx), this.tiles.get(tileIdx) - 8, true);
                        GameData.getAdventurers()[players.indexOf(this.tiles.get(tileIdx) - 8)].setPos(i, j);
                    }
                    else {
                        tileMap[i][j] = new Blocks(tiles.get(tileIdx), true);
                    }
                    tileIdx ++;
                }
            }
        }
    }

    public Blocks getTile(int x, int y){
        return tileMap[x][y];
    }

}
