package com.esr.service.game.data;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class BlockData {
    private int tileId;
    private TileStatus status;
    private String img;
    private int playerID = 0;
    private boolean isExist;

    public BlockData(int tileId, int playerID, boolean isExist) {
        this.tileId = tileId;
        this.status = TileStatus.Normal;
        this.img = "/Tiles/" + tileId + ".png";
        this.playerID = playerID;
        this.isExist = isExist;
    }

    public BlockData(int tileId, boolean isExist){
        this.tileId = tileId;
        this.status = TileStatus.Normal;
        this.img = "/Tiles/" + tileId + ".png";
        this.isExist = isExist;
    }

    public BlockData(boolean isExist){
        this.isExist = isExist;
    }

    public void SinkTile() {
        if (status == TileStatus.Normal){
            status = TileStatus.Flooded;
            img = "/SubmersedTiles/" + tileId + ".png";
        }
        else if (status == TileStatus.Flooded){
            status = TileStatus.Sunk;
            img = null;
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

    public void setPlayer(int playerID){
        this.playerID = playerID;
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

    public int getPlayerID() {
        return playerID;
    }

    public boolean isExist() {
        return isExist;
    }
}
