package com.esr.service.game.component.adventurer;

/**
 * @Description
 * @Author William
 * @Date 2020/11/25
 * @Version 1.0
 **/
public class Explorer extends Adventurer{
    public Explorer(int order, int pos) {
        super(order, pos);
        this.name = "Explorer";
        this.id = 2;
    }
}
