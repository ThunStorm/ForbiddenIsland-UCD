package com.esr.service.game.data;

import com.esr.service.game.component.adventurer.Adventurer;

import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class Blocks {
    private int tileId;
    private TileStatus status;
    private String img;
    private boolean isExist;
    private ArrayList<Integer> adventurersOnBoard;

    public Blocks(int tileId, int playerID, boolean isExist) {
        this.adventurersOnBoard = new ArrayList<>();
        this.tileId = tileId;
        this.status = TileStatus.Normal;
        this.img = "/Tiles/" + tileId + ".png";
        this.adventurersOnBoard.add(playerID);
        this.isExist = isExist;
    }

    public Blocks(int tileId, boolean isExist){
        this.tileId = tileId;
        this.status = TileStatus.Normal;
        this.img = "/Tiles/" + tileId + ".png";
        this.isExist = isExist;
    }

    public Blocks(boolean isExist){
        this.isExist = isExist;
    }

    public void moveOnto(int playerID) {
        this.adventurersOnBoard.add(playerID);
    }

    public int moveOff(Adventurer adventurer){
        int adventurerId =  adventurer.getId();
        int playerOut = adventurersOnBoard.get(adventurerId);
        adventurersOnBoard.remove((Integer) adventurerId);
        return adventurerId;
    }

    public boolean canPassTo(Adventurer sender, Adventurer receiver){
        return adventurersOnBoard.contains(sender.getId()) && adventurersOnBoard.contains(receiver.getId());
    }

    public void SinkTile() {
        if (status == TileStatus.Normal){
            status = TileStatus.Flooded;
            img = "/SubmersedTiles/" + tileId + ".png";
        }
        else if (status == TileStatus.Flooded){
            status = TileStatus.Sunk;
            img = null;
            isExist = false;

        }
        else {
            System.out.println("ERROR! Tile has sunk");
        }
    }

    public void ShoreUp() {
        if (status == TileStatus.Flooded){
            status = TileStatus.Normal;
            this.img = "/Tiles/" + tileId + ".png";
        }
        else {
            System.out.println("ERROR! Tile has sunk or normal");
        }
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

    public ArrayList<Integer> getPlayerID(){ return adventurersOnBoard; }
}
