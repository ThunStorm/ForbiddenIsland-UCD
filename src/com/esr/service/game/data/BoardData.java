package com.esr.service.game.data;

import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.GameData;
import com.esr.utils.Map;

import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/14
 * @Version 1.0
 **/
public class BoardData {
    private Block[][] tileMap;
    private ArrayList<Integer> tiles;
    private ArrayList<Integer> sunkList;
    private boolean canMove;
    private boolean canShoreUp;

    public BoardData(ArrayList<Integer> players, ArrayList<Integer> tiles) {
        this.tiles = tiles;
        sunkList = new ArrayList<>();
        tileMap = new Block[6][6];
        int tileIdx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (Map.blankLayout.contains(i * 6 + j)) {
                    tileMap[i][j] = new Block(false);
                }
                else {
                    if (players.contains(this.tiles.get(tileIdx) - 9)){
                        tileMap[i][j] = new Block(this.tiles.get(tileIdx), this.tiles.get(tileIdx) - 9, true);
                        GameData.getAdventurers()[players.indexOf(this.tiles.get(tileIdx) - 9)].setPos(i, j);
                    }
                    else {
                        tileMap[i][j] = new Block(this.tiles.get(tileIdx), true);
                    }
                    tileIdx ++;
                }
            }
        }
    }

    public Block getTile(int x, int y){
        return tileMap[x][y];
    }

    public Block[][] getTileMap() { return tileMap; }

    public void sinkTiles(ArrayList<Integer> sinkTiles){
        sunkList.clear();
        for (int sinkTile : sinkTiles){
            int[] coords = Map.coordinatesMatcher.get(this.tiles.indexOf(sinkTile));
            tileMap[coords[0]][coords[1]].SinkTile();
        }
        UpdaterAgent.getBoardUpdater().guiUpdate();
    }

    public boolean isCanMove() { return canMove; }
    public void setCanMove(boolean canMove) { this.canMove = canMove; }

    public boolean isCanShoreUp(){ return canShoreUp;}
    public void setCanShoreUp(boolean canShoreUp){this.canShoreUp = canShoreUp;}
}
