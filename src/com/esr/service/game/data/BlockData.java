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
    private int playerID;

    public BlockData(int tileId, TileStatus status, String img, int playerID) {
        this.tileId = tileId;
        this.status = status;
        this.img = img;
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
}
