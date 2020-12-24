package com.esr.service.game.data;

import com.esr.gui.updater.LogAgent;
import com.esr.service.game.Game;
import com.esr.service.game.component.adventurer.Adventurer;

import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class Block {
    // this class stores information of a single tile
    private int tileId = -1;
    private TileStatus status;
    private String img;
    private String imgFolder;
    private String imgFile;
    private boolean isExist;
    private ArrayList<Integer> adventurersOnBoard;
    private boolean isCaptured = false;

    // initial a block with a pawn on
    public Block(int tileId, int playerID, boolean isExist) {
        this.adventurersOnBoard = new ArrayList<>();
        this.tileId = tileId;
        this.status = TileStatus.Normal;
        this.imgFolder = "/Tiles/";
        this.imgFile = tileId + ".png";
        this.img = imgFolder + imgFile;
        this.adventurersOnBoard.add(playerID);
        this.isExist = isExist;
    }

    // initial a block without a pawn on
    public Block(int tileId, boolean isExist) {
        this.adventurersOnBoard = new ArrayList<>();
        this.tileId = tileId;
        this.status = TileStatus.Normal;
        this.imgFolder = "/Tiles/";
        this.imgFile = tileId + ".png";
        this.img = imgFolder + imgFile;
        this.isExist = isExist;
    }

    // initial a place-holding blank block
    public Block(boolean isExist) {
        this.isExist = isExist;
    }

    // store a player when moving onto the tile
    public void MoveOnto(int playerID) {
        this.adventurersOnBoard.add(playerID);
    }

    // remove a player when moving off the tile
    public void MoveOff(Adventurer adventurer) {
        adventurersOnBoard.remove((Integer) adventurer.getId());
    }

    // confirm sender and receiver are on the same tile
    public boolean CanPassTo(Adventurer sender, Adventurer receiver) {
        return adventurersOnBoard.contains(sender.getId()) && adventurersOnBoard.contains(receiver.getId());
    }

    // shore up a tile and update img
    public void ShoreUp() {
        if (status == TileStatus.Flooded) {
            status = TileStatus.Normal;
            this.imgFolder = "/Tiles/";
            this.img = imgFolder + imgFile;
        } else {
            System.out.println("ERROR! Tile is not flooded");
        }
    }

    // sink a tile and update img
    public boolean SinkTile() {
        if (status == TileStatus.Normal) {
            status = TileStatus.Flooded;
            imgFolder = "/SubmersedTiles/";
            img = imgFolder + imgFile;
            return false;
        } else if (status == TileStatus.Flooded) {
            status = TileStatus.Sunk;
            img = null;
            isExist = false;
            if (tileId == 14) {
                Game.GameComplete(false);
                LogAgent.logMessenger("[!] Fool's Landing Is Flooded!");
            }
            return true;
        } else {
            System.out.println("ERROR! This tile has sunk");
            return true;
        }
    }

    // used to update status and img, after successfully capturing a figurine from the tile
    public void setCaptured() {
        isCaptured = true;
        this.imgFile = tileId + 24 + ".png";
        this.img = imgFolder + imgFile;
    }

    // check whether figurine still on the tile
    public boolean isUnCaptured() {
        return !isCaptured;
    }

    // get tile id
    public int getTileId() {
        return tileId;
    }

    // get tile status
    public TileStatus getStatus() {
        return status;
    }

    // get tile img
    public String getImg() {
        return img;
    }

    // get tile existence
    public boolean isExist() {
        return isExist;
    }

    // get the list of players on the tile
    public ArrayList<Integer> getPlayerOnBoard() {
        return adventurersOnBoard;
    }
}
