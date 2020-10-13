package com.esr.cards;

public abstract class Card {
    protected boolean isFront = false;
    protected boolean isDiscarded = false;
    protected int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
