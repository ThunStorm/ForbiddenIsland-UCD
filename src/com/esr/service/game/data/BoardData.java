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
            if (tileMap[coords[0]][coords[1]].SinkTile()){
                GameData.getFloodDeck().RemoveFloodCard(sinkTile);
            }
        }
        UpdaterAgent.getBoardUpdater().guiUpdate();
    }

    public boolean isCanMove() { return canMove; }
    public void setCanMove(boolean canMove) { this.canMove = canMove; }

    public boolean isCanShoreUp(){ return canShoreUp;}
    public void setCanShoreUp(boolean canShoreUp){this.canShoreUp = canShoreUp;}

    public boolean isShrinesFlooded(){
        int[] isShrinesFlooded = {1, 1, 1, 1};
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                switch (tileMap[i][j].getTileId()) {
                    case 1:
                    case 2:
                        if(!tileMap[i][j].isExist() && !tileMap[i][j].isCaptured()){
                            isShrinesFlooded[0]--;
                        }
                        break;
                    case 3:
                    case 4:
                        if(!tileMap[i][j].isExist() && !tileMap[i][j].isCaptured()){
                            isShrinesFlooded[1]--;
                        }
                        break;
                    case 5:
                    case 6:
                        if(!tileMap[i][j].isExist() && !tileMap[i][j].isCaptured()){
                            isShrinesFlooded[2]--;
                        }
                        break;
                    case 7:
                    case 8:
                        if(!tileMap[i][j].isExist() && !tileMap[i][j].isCaptured()){
                            isShrinesFlooded[3]--;
                        }
                        break;
                }
            }
        }
        for (int isFlooded : isShrinesFlooded){
            if (isFlooded == -1){
                return true;
            }
        }
        return false;
    }
}
