package com.esr.service.game.component.adventurer;

/**
 * @Description
 * @Author William
 * @Date 2020/11/24
 * @Version 1.0
 **/
public class Engineer extends Adventurer {

    private int shoreUpCount = 1;

    public Engineer(int order) {
        super(order, "Engineer");
        this.id = 1;
    }

    public int getShoreUpCount() {
        return shoreUpCount;
    }

    public void ShoreUp() {
        this.shoreUpCount -= 1;
    }

    public void resetShoreUpCount() {
        this.shoreUpCount = 1;
    }
}
