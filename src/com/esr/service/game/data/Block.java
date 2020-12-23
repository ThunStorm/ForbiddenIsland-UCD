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
    private int tileId = -1;
    private TileStatus status;
    private String img;
    private String imgFolder;
    private String imgFile;
    private boolean isExist;
    private ArrayList<Integer> adventurersOnBoard;
    private boolean isCaptured = false;
    private int floodedOffset = 0;

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

    public Block(int tileId, boolean isExist) {
        this.adventurersOnBoard = new ArrayList<>();
        this.tileId = tileId;
        this.status = TileStatus.Normal;
        this.imgFolder = "/Tiles/";
        this.imgFile = tileId + ".png";
        this.img = imgFolder + imgFile;
        this.isExist = isExist;
    }

    public Block(boolean isExist) {
        this.isExist = isExist;
    }

    public void MoveOnto(int playerID) {
        this.adventurersOnBoard.add(playerID);
    }

    public void MoveOff(Adventurer adventurer) {
//        int playerOut = adventurersOnBoard.get(adventurersOnBoard.indexOf(adventurer.getId()));
        adventurersOnBoard.remove((Integer) adventurer.getId());
    }

    public void ShoreUp() {
        if (status == TileStatus.Flooded) {
            status = TileStatus.Normal;
            this.imgFolder = "/Tiles/";
            this.img = imgFolder + imgFile;
        } else {
            System.out.println("ERROR! Tile is not flooded");
        }
    }

    public boolean CanPassTo(Adventurer sender, Adventurer receiver) {
        return adventurersOnBoard.contains(sender.getId()) && adventurersOnBoard.contains(receiver.getId());
    }

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
                System.out.println("Fool's landing is flooded!");
                LogAgent.logMessenger("Fool's landing is flooded!");
            }
            return true;
        } else {
            System.out.println("ERROR! Tile has sunk");
            return true;
        }
    }

    public void setCaptured() {
        isCaptured = true;
        this.imgFile = tileId + 24 + ".png";
        this.img = imgFolder + imgFile;
    }

    public boolean isUnCaptured() {
        return !isCaptured;
    }

    public int getTileId() {
        return tileId;
    }

    public TileStatus getStatus() {
        return status;
    }

    public String getImg() {
        return img;
    }

    public boolean isExist() {
        return isExist;
    }

    public ArrayList<Integer> getPlayerOnBoard() {
        return adventurersOnBoard;
    }
}
