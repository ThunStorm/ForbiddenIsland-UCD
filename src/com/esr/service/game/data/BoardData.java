package com.esr.service.game.data;

import com.esr.gui.updater.BoardUpdater;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.service.game.component.cards.Tile;
import com.esr.utils.Map;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description
 * @Author William
 * @Date 2020/12/14
 * @Version 1.0
 **/
public class BoardData {
    private Blocks[][] tileMap;
    private ArrayList<Integer> tiles;
    private ArrayList<Integer> sunkList;

    public BoardData(ArrayList<Integer> players, ArrayList<Integer> tiles) {
        this.tiles = tiles;
        sunkList = new ArrayList<>();
        tileMap = new Blocks[6][6];
        int tileIdx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (Map.blankLayout.contains(i * 6 + j)) {
                    tileMap[i][j] = new Blocks(false);
                }
                else {
                    if (players.contains(this.tiles.get(tileIdx) - 9)){
                        tileMap[i][j] = new Blocks(this.tiles.get(tileIdx), this.tiles.get(tileIdx) - 9, true);
                        GameData.getAdventurers()[players.indexOf(this.tiles.get(tileIdx) - 9)].setPos(i, j);
                    }
                    else {
                        tileMap[i][j] = new Blocks(this.tiles.get(tileIdx), true);
                    }
                    tileIdx ++;
                }
            }
        }
    }

    public Blocks getTile(int x, int y){
        return tileMap[x][y];
    }

    public void sinkTile(ArrayList<Integer> sinkTiles){
        sunkList.clear();
        for (int sinkTile : sinkTiles){
            int[] coords = Map.coordinatesMatcher.get(this.tiles.indexOf(sinkTile));
            tileMap[coords[0]][coords[1]].SinkTile();
            if (tileMap[coords[0]][coords[1]].getStatus() == TileStatus.Sunk){
                sunkList.add(sinkTile + 100);
            }
            else {
                sunkList.add(sinkTile);
            }
        }
        UpdaterAgent.getBoardUpdater().guiUpdate(sinkTiles);
    }

}
