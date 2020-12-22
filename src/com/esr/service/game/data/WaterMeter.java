package com.esr.service.game.data;

import com.esr.gui.updater.LogAgent;
import com.esr.service.game.Game;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class WaterMeter {
    private int waterLevel;
    private int floodCardCount;
    private String img;

    public WaterMeter(int waterLevel) {
        this.waterLevel = waterLevel;
        this.img = "/WaterMeter/" + waterLevel + ".png";
        setFloodCardCount();
    }

    public void WaterRise(){
        waterLevel += 1;
        img = "/WaterMeter/" + waterLevel + ".png";
        setFloodCardCount();
        if (waterLevel == 10){
            LogAgent.logMessenger("Water level reaches the skull and crossbones");
            Game.GameComplete(false);
        }
    }

    private void setFloodCardCount(){
        switch (waterLevel){
            case 1 :
            case 2 :
                floodCardCount = 2;
                break;
            case 3 :
            case 4 :
            case 5 :
                floodCardCount = 3;
                break;
            case 6 :
            case 7 :
                floodCardCount = 4;
                break;
            case 8 :
            case 9 :
                floodCardCount = 5;
                break;
        }
    }

    public int getFloodCardCount() {
        return floodCardCount;
    }

    public String getImg() {
        return img;
    }
}
