package com.esr.service.game.data;

import com.esr.gui.updater.LogAgent;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.Game;
import com.esr.service.game.GameData;
import com.esr.utils.Audio;
import com.esr.utils.Constant;
import com.esr.utils.Map;

import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/14
 * @Version 1.0
 **/
public class BoardData {
    // this class stores the game board data which is constituted by Blocks
    private final Block[][] tileMap;
    private final ArrayList<Integer> tiles;
    private boolean canMove;
    private boolean canShoreUp;

    // initialize the board data
    public BoardData(ArrayList<Integer> players, ArrayList<Integer> tiles) {
        this.tiles = tiles;
        tileMap = new Block[6][6];
        int tileIdx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (Map.blankLayout.contains(i * 6 + j)) {
                    tileMap[i][j] = new Block(false);
                } else {
                    if (players.contains(this.tiles.get(tileIdx) - 9)) {
                        tileMap[i][j] = new Block(this.tiles.get(tileIdx), this.tiles.get(tileIdx) - 9, true);
                        GameData.getAdventurers()[players.indexOf(this.tiles.get(tileIdx) - 9)].setPos(i, j);
                    } else {
                        tileMap[i][j] = new Block(this.tiles.get(tileIdx), true);
                    }
                    tileIdx++;
                }
            }
        }
    }

    // sink a list of tiles
    public void sinkTiles(ArrayList<Integer> sinkTiles) {
        for (int sinkTile : sinkTiles) {
            int[] coords = Map.coordinatesMatcher.get(this.tiles.indexOf(sinkTile));
            // if the tile is removed
            if (tileMap[coords[0]][coords[1]].SinkTile()) {
                GameData.getFloodDeck().RemoveFloodCard(sinkTile);
                // if there are players on the removed tile
                if (tileMap[coords[0]][coords[1]].getPlayerOnBoard().size() != 0) {
                    for (int player : tileMap[coords[0]][coords[1]].getPlayerOnBoard()) {
                        LogAgent.logMessenger(Map.adventurerMatcher.get(player) + " Has Fallen Into Sea");
                    }
                    Game.setNeed2save(true);
                    Game.setPlayerIDinWater(tileMap[coords[0]][coords[1]].getPlayerOnBoard());
                    tileMap[coords[0]][coords[1]].getPlayerOnBoard().clear();
                }
            }
        }
        // start swim processes
        if (Game.isNeed2save()) {
            Game.setInFakeRound(true);
            Game.setFakeRoundNum(Game.getRoundNum());
            Game.SavePlayersRound();
            if (Constant.AUDIO_ON_OFF) {
                Audio.SPLASH.Play();
            }
        }
        UpdaterAgent.getBoardUpdater().guiUpdate();
    }

    // set availability of movement
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    // set availability of reinforce
    public void setCanShoreUp(boolean canShoreUp) {
        this.canShoreUp = canShoreUp;
    }

    // check if all shrines are sunk with figurines
    public boolean isShrinesFlooded() {
        int[] isShrinesFlooded = {1, 1, 1, 1};
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                switch (tileMap[i][j].getTileId()) {
                    case 1:
                    case 2:
                        if (!tileMap[i][j].isExist() && tileMap[i][j].isUnCaptured()) {
                            isShrinesFlooded[0]--;
                        }
                        break;
                    case 3:
                    case 4:
                        if (!tileMap[i][j].isExist() && tileMap[i][j].isUnCaptured()) {
                            isShrinesFlooded[1]--;
                        }
                        break;
                    case 5:
                    case 6:
                        if (!tileMap[i][j].isExist() && tileMap[i][j].isUnCaptured()) {
                            isShrinesFlooded[2]--;
                        }
                        break;
                    case 7:
                    case 8:
                        if (!tileMap[i][j].isExist() && tileMap[i][j].isUnCaptured()) {
                            isShrinesFlooded[3]--;
                        }
                        break;
                }
            }
        }
        for (int isFlooded : isShrinesFlooded) {
            if (isFlooded == -1) {
                return true;
            }
        }
        return false;
    }

    // check if can be shored up
    public boolean isCanShoreUp() {
        return canShoreUp;
    }

    // check if can move
    public boolean isCanMove() {
        return canMove;
    }

    // get tile Block
    public Block getTile(int x, int y) {
        return tileMap[x][y];
    }

    // get whole game board
    public Block[][] getTileMap() {
        return tileMap;
    }
}
