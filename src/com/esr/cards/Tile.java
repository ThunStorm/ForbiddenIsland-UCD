package com.esr.cards;

import com.esr.utils.Map;

public class Tile extends Card{
    private boolean isSunken = false;
    private int pos;
    private int x, y;

    public Tile(int id, int pos) {
        super(id);
        this.pos = pos;
        this.x = Map.coordinatesMatcher.get(pos)[0];
        this.y = Map.coordinatesMatcher.get(pos)[1];

    }
    public void SinkTile(){

    }
    public void ShoreUp(){

    }
}
