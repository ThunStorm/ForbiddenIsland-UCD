package com.esr.adventurer;

/**
 * @Description
 * @Author William
 * @Date 2020/11/25
 * @Version 1.0
 **/
public class Pilot extends Adventurer{
    public Pilot(int order, int pos) {
        super(order, pos);
        this.name = "Pilot";
        this.id = 5;
    }
}
