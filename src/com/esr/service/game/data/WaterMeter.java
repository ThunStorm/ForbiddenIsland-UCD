package com.esr.service.game.data;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class WaterMeter {
    private int waterLevel;
    private String img;

    public WaterMeter(int waterLevel) {
        this.waterLevel = waterLevel;
        this.img = "/WaterMeter/" + waterLevel + ".png";
    }

    public void WaterRise(){
        waterLevel += 1;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public String getImg() {
        return img;
    }
}
